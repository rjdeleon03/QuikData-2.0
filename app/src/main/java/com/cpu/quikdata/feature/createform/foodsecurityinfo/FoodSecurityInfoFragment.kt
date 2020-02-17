package com.cpu.quikdata.feature.createform.foodsecurityinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsFragment
import kotlinx.android.synthetic.main.fragment_food_security_info.*

class FoodSecurityInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        @JvmStatic
        fun start() = FoodSecurityInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClipping(foodSecurityInfoViewPager)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FoodSecurityImpactFragment.start(), getString(R.string.food_security_impact_title))
        pagerAdapter.addFragment(FoodSecurityCopingFragment.start(), getString(R.string.food_security_coping_title))
        pagerAdapter.addFragment(FoodSecurityNeedsFragment.start(), getString(R.string.food_security_needs_title))
        pagerAdapter.addFragment(FoodSecurityAssistanceFragment.start(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(FoodSecurityGapsFragment.start(), getString(R.string.food_security_gaps_title))
        foodSecurityInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
