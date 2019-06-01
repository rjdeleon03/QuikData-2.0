package com.cpu.quikdata.feature.createform.foodsecurityinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsFragment
import kotlinx.android.synthetic.main.fragment_food_security_info.*

class FoodSecurityInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        fun newInstance() = FoodSecurityInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FoodSecurityImpactFragment.newInstance(), getString(R.string.food_security_impact_title))
        pagerAdapter.addFragment(FoodSecurityCopingFragment.newInstance(), getString(R.string.food_security_coping_title))
        pagerAdapter.addFragment(FoodSecurityNeedsFragment.newInstance(), getString(R.string.food_security_needs_title))
        foodSecurityInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
