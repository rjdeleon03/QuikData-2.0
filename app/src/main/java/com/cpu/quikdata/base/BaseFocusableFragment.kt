package com.cpu.quikdata.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

abstract class BaseFocusableFragment: DaggerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.requestFocus()
    }
}