package com.cpu.quikdata.base

import com.cpu.quikdata.R
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity

abstract class BaseCreateFormSectionFragment : BaseFragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as CreateFormActivity).setSubtitle(getString(R.string.create_form_subtitle))
    }

    protected fun setSubtitle(subtitle: CharSequence?) {
        (requireActivity() as CreateFormActivity).setSubtitle(subtitle)
    }
}