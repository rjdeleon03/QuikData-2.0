package com.cpu.quikdata.base

import androidx.fragment.app.Fragment
import com.cpu.quikdata.R
import com.cpu.quikdata.feature.createform.CreateFormActivity

abstract class BaseCreateFormSectionFragment : Fragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        (activity!! as CreateFormActivity).setSubtitle(getString(R.string.create_form_subtitle))
    }

    protected fun setSubtitle(subtitle: CharSequence?) {
        (activity!! as CreateFormActivity).setSubtitle(subtitle)
    }
}