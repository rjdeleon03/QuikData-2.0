package com.cpu.quikdata.base

import android.os.Bundle
import android.view.View

abstract class BaseFocusableFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.requestFocus()
    }
}