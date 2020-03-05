package com.cpu.quikdata.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.feature.createform.CreateFormViewModel

abstract class BaseCreateFormFragment: BaseFragment() {

    protected lateinit var mParentViewModel: CreateFormViewModel
    protected lateinit var mFactory: ViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProvider(activity!!).get(CreateFormViewModel::class.java)
        mFactory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser && activity != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val focus = activity!!.currentFocus
            if (focus != null) {
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
                focus.clearFocus()
            }
        }
    }
}