package com.cpu.quikdata.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.di.app.module.DaggerViewModelFactory
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.feature.createform.activity.CreateFormViewModel
import javax.inject.Inject

abstract class BaseCreateFormFragment: BaseFragment() {

    @Inject lateinit var mViewModelFactory: DaggerViewModelFactory

    lateinit var mParentViewModel: CreateFormViewModel

    protected lateinit var mFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as CreateFormActivity).createFormComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = mViewModelFactory.create(CreateFormViewModel::class.java)
        mFactory = ViewModelFactory(requireActivity().application, mParentViewModel.formId)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser && activity != null) {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val focus = requireActivity().currentFocus
            if (focus != null) {
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
                focus.clearFocus()
            }
        }
    }
}