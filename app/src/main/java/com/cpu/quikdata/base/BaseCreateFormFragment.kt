package com.cpu.quikdata.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cpu.quikdata.feature.createform.CreateFormViewModel

abstract class BaseCreateFormFragment: Fragment() {

    protected lateinit var mParentViewModel: CreateFormViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)
    }
}