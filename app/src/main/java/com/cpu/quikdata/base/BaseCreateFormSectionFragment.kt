package com.cpu.quikdata.base

import androidx.fragment.app.Fragment
import com.cpu.quikdata.feature.createform.CreateFormActivity

abstract class BaseCreateFormSectionFragment : Fragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        (activity!! as CreateFormActivity).setSubtitle()
    }

    protected fun setSubtitle(subtitle: CharSequence?) {
        (activity!! as CreateFormActivity).setSubtitle(subtitle)
    }
}