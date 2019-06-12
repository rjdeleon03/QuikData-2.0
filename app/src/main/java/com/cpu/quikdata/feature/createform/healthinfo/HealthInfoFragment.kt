package com.cpu.quikdata.feature.createform.healthinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsFragment
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialFragment
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsFragment
import kotlinx.android.synthetic.main.fragment_health_info.*

class HealthInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HealthInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(DiseasesFragment.newInstance(), getString(R.string.diseases_title))
        pagerAdapter.addFragment(SpecialNeedsFragment.newInstance(), getString(R.string.special_needs_title))
        pagerAdapter.addFragment(PsychosocialFragment.newInstance(), getString(R.string.psychosocial_title))
        pagerAdapter.addFragment(HealthCopingFragment.newInstance(), getString(R.string.health_coping_title))
        pagerAdapter.addFragment(HealthAssistanceFragment.newInstance(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(HealthGapsFragment.newInstance(), getString(R.string.health_gaps_title))
        healthInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
