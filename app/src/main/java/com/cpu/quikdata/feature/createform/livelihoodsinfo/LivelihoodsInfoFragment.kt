package com.cpu.quikdata.feature.createform.livelihoodsinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
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
        fun newInstance() = LivelihoodsInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(IncomeBeforeFragment.newInstance(), getString(R.string.income_before_title))
        pagerAdapter.addFragment(IncomeAfterFragment.newInstance(), getString(R.string.income_after_title))
        pagerAdapter.addFragment(EstimatedDamageFragment.newInstance(), getString(R.string.estimated_damage_title))
        pagerAdapter.addFragment(LivelihoodsCopingFragment.newInstance(), getString(R.string.livelihoods_coping_title))
        pagerAdapter.addFragment(LivelihoodsNeedsFragment.newInstance(), getString(R.string.livelihoods_needs_title))
        pagerAdapter.addFragment(LivelihoodsAssistanceFragment.newInstance(), getString(R.string.text_assistance))
        pagerAdapter.addFragment(LivelihoodsGapsFragment.newInstance(), getString(R.string.livelihoods_gaps_title))
        livelihoodsInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
