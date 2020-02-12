package com.cpu.quikdata.base

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.common.ViewModelProviderFactory
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseCreateFormFragment: DaggerFragment() {

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mParentViewModelFactory: CreateFormViewModel.Factory

    protected lateinit var mFactory: ViewModelFactory

    protected val mParentViewModel: CreateFormViewModel? by lazy {
        mParentViewModelFactory.create("")
    }

//    protected lateinit var mParentViewModel: CreateFormViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        mParentViewModel = ViewModelProvider(activity!!).get(CreateFormViewModel::class.java)

        mParentViewModel?.let {
            mFactory = ViewModelFactory(activity!!.application, it.formId)
        }
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