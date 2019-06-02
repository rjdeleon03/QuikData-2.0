package com.cpu.quikdata.feature.createform.shelterinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsFragment
import kotlinx.android.synthetic.main.fragment_shelter_info.*

class ShelterInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        fun newInstance() = ShelterInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(HouseDamageFragment.newInstance(), getString(R.string.house_damage_title))
        pagerAdapter.addFragment(ShelterCopingFragment.newInstance(), getString(R.string.shelter_coping_title))
        pagerAdapter.addFragment(ShelterNeedsFragment.newInstance(), getString(R.string.shelter_needs_title))
        pagerAdapter.addFragment(ShelterAssistanceFragment.newInstance(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(ShelterGapsFragment.newInstance(), getString(R.string.shelter_gaps_title))
        shelterInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
