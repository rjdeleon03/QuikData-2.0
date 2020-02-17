package com.cpu.quikdata.feature.createform.livelihoodsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsFragment
import kotlinx.android.synthetic.main.fragment_livelihoods_info.*

class LivelihoodsInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        @JvmStatic
        fun start() = LivelihoodsInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClipping(livelihoodsInfoViewPager)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(IncomeBeforeFragment.start(), getString(R.string.income_before_title))
        pagerAdapter.addFragment(IncomeAfterFragment.start(), getString(R.string.income_after_title))
        pagerAdapter.addFragment(EstimatedDamageFragment.start(), getString(R.string.estimated_damage_title))
        pagerAdapter.addFragment(LivelihoodsCopingFragment.start(), getString(R.string.livelihoods_coping_title))
        pagerAdapter.addFragment(LivelihoodsNeedsFragment.start(), getString(R.string.livelihoods_needs_title))
        pagerAdapter.addFragment(LivelihoodsAssistanceFragment.start(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(LivelihoodsGapsFragment.start(), getString(R.string.livelihoods_gaps_title))
        livelihoodsInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
