package com.cpu.quikdata.feature.createform.watersanitationinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsFragment
import kotlinx.android.synthetic.main.fragment_water_sanitation_info.*

class WaterSanitationInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        @JvmStatic
        fun start() = WaterSanitationInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_water_sanitation_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClipping(washViewPager)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(WashConditionsFragment.start(), getString(R.string.wash_conditions_title))
        pagerAdapter.addFragment(WashCopingFragment.start(), getString(R.string.wash_coping_title))
        pagerAdapter.addFragment(WashAssistanceFragment.start(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(WashGapsFragment.start(), getString(R.string.wash_gaps_title))
        washViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
