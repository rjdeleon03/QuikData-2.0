package com.cpu.quikdata.feature.main.newforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.common.*
import com.cpu.quikdata.data.form.FormComplete
import com.cpu.quikdata.dialog.ProgressDialogFragment
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_new_forms.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*
import javax.inject.Inject

class NewFormsFragment : DaggerFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = NewFormsFragment()
    }

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var mViewModel: NewFormsViewModel
    private lateinit var mAdapter: NewFormsAdapter
    private var mDialog: ProgressDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_forms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClipping(newFormsMainLayout)
        setupViewModel()

        val dialog = childFragmentManager.findFragmentByTag(ProgressDialogFragment.TAG) as? ProgressDialogFragment
        if (dialog != null) mDialog = dialog

        val submitListener = { fc: FormComplete ->
            mViewModel.submitForm(fc.form!!.id)
            showProgressDialog()
        }
        val deleteListener = { fc: FormComplete -> showConfirmationDialog({ mViewModel.deleteForm(fc) },
            R.string.form_item_delete_confirmation,
            R.layout.dialog_form_item_delete,
            R.string.form_item_deleted)}
        mAdapter = NewFormsAdapter(context!!, submitListener, deleteListener)

        newFormsRecyclerView.recyclerView.adapter = mAdapter
        newFormsAddButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.newInstance(context!!, formId, basicMode = true)
        }
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this, providerFactory).get(NewFormsViewModel::class.java)

        mViewModel = ViewModelProvider(this).get(NewFormsViewModel::class.java)
        mViewModel.newForms.observe(viewLifecycleOwner, Observer { forms ->
            newFormsRecyclerView.updateDisplayBasedOnItemCount(forms.size)
            mAdapter.setForms(forms)
        })
        mViewModel.saveResult.observeProgress(viewLifecycleOwner, {
            mDialog?.dismiss()
            showToast(R.string.form_item_submission_success)
        }, {
            mDialog?.dismiss()
            showToast(R.string.form_item_submission_failed)
        }, {
            showToast(R.string.form_item_submission_cancelled)
        }, {
            mDialog?.updateBasedOnProgress(it)
        })
    }

    private fun showProgressDialog() {
        mDialog?.dismiss()
        mDialog = ProgressDialogFragment.newInstance(textId = R.layout.dialog_progress)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
        mDialog?.setOnDialogCanceledListener {
            mViewModel.cancelSubmission()
        }
    }
}
