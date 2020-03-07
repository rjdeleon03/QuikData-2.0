package com.cpu.quikdata.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.di.createform.activity.CreateFormComponent
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.feature.createform.activity.CreateFormViewModel

abstract class BaseCreateFormFragment: BaseFragment() {

    protected val mParentViewModel: CreateFormViewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(CreateFormViewModel::class.java)
    }

    protected val mCreateFormComponent: CreateFormComponent
        get() = (requireActivity() as CreateFormActivity).createFormComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as CreateFormActivity).createFormComponent.inject(this)
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