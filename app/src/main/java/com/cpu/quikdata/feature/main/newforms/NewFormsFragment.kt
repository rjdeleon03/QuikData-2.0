package com.cpu.quikdata.feature.main.newforms

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseFragment
import com.cpu.quikdata.common.*
import com.cpu.quikdata.data.form.FormComplete
import com.cpu.quikdata.dialog.ProgressDialogFragment
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.fragment_new_forms.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*

class NewFormsFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = NewFormsFragment()
    }

    private val mViewModel: NewFormsViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(NewFormsViewModel::class.java)
    }

    private lateinit var mAdapter: NewFormsAdapter
    private var mDialog: ProgressDialogFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mAppComponent.newFormsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_forms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClipping(newFormsMainLayout)

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
        mAdapter = NewFormsAdapter(requireContext(), submitListener, deleteListener)

        newFormsRecyclerView.recyclerView.adapter = mAdapter
        newFormsAddButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.newInstance(requireContext(), formId, basicMode = true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
