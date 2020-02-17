package com.cpu.quikdata.feature.createform.formdetailsandbaseline


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsFragment
import kotlinx.android.synthetic.main.fragment_form_details_and_baseline.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FormDetailsAndBaselineFragment : BaseCreateFormSectionFragment() {

    companion object {
        @JvmStatic
        fun start() = FormDetailsAndBaselineFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_details_and_baseline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClipping(formDetailsViewPager)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FormDetailsFragment.start(), getString(R.string.form_details_title))
        pagerAdapter.addFragment(BaselineDataFragment.start(), getString(R.string.baseline_data_title))
        formDetailsViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }
}
