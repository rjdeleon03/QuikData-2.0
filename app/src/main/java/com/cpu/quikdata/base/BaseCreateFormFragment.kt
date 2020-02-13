package com.cpu.quikdata.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.cpu.quikdata.di.module.viewmodel.ViewModelProviderFactory
import com.cpu.quikdata.feature.createform.CreateFormAltViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseCreateFormFragment: DaggerFragment() {

    @Inject
    lateinit var mViewModelProviderFactory: ViewModelProviderFactory

    protected val mParentViewModel: CreateFormAltViewModel by lazy {
        mViewModelProviderFactory.create(CreateFormAltViewModel::class.java)
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