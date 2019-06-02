package com.cpu.quikdata.feature.createform.livelihoodsinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsFragment
import kotlinx.android.synthetic.main.fragment_livelihoods_info.*

class LivelihoodsInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
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
        pagerAdapter.addFragment(EstimatedDamageFragment.newInstance(), getString(R.string.estimated_damage_title))
        pagerAdapter.addFragment(LivelihoodsCopingFragment.newInstance(), getString(R.string.livelihoods_coping_title))
        pagerAdapter.addFragment(LivelihoodsNeedsFragment.newInstance(), getString(R.string.livelihoods_needs_title))
        pagerAdapter.addFragment(LivelihoodsGapsFragment.newInstance(), getString(R.string.livelihoods_gaps_title))
        livelihoodsInfoViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
