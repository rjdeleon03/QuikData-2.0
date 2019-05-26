package com.cpu.quikdata.feature.createform

import androidx.fragment.app.Fragment

abstract class CreateFormBaseFragment : Fragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        (activity!! as CreateFormActivity).setSubtitle()
    }

    protected fun setSubtitle(subtitle: CharSequence?) {
        (activity!! as CreateFormActivity).setSubtitle(subtitle)
    }
}