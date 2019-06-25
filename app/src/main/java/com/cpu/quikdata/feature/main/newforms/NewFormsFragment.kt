package com.cpu.quikdata.feature.main.newforms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.showConfirmationDialog
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.fragment_new_forms.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*

class NewFormsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = NewFormsFragment()
    }

    private lateinit var mViewModel: NewFormsViewModel
    private lateinit var mAdapter: NewFormsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_forms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClipping(newFormsMainLayout)
        mAdapter = NewFormsAdapter(context!!) { showConfirmationDialog({ mViewModel.deleteForm(it) },
            R.string.form_item_delete_confirmation,
            R.layout.dialog_form_item_delete) }
        newFormsRecyclerView.recyclerView.adapter = mAdapter
        newFormsAddButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.newInstance(context!!, formId, basicMode = true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(NewFormsViewModel::class.java)
        mViewModel.newForms.observe(viewLifecycleOwner, Observer { forms ->
            newFormsRecyclerView.updateDisplayBasedOnItemCount(forms.size)
            mAdapter.setForms(forms)
        })
    }
}
