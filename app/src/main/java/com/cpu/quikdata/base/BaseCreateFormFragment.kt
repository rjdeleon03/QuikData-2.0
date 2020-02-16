package com.cpu.quikdata.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.cpu.quikdata.di.module.viewmodel.ViewModelProviderFactory
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseCreateFormFragment: DaggerFragment() {

    @Inject
    lateinit var mViewModelProviderFactory: ViewModelProviderFactory

    protected val mParentViewModel: CreateFormViewModel by lazy {
        mViewModelProviderFactory.create(CreateFormViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        activity?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val focus = currentFocus
            if (focus != null) {
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
                focus.clearFocus()
            }
        }
    }
}