package com.cpu.quikdata.feature.createform.generalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.cpu.quikdata.R
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.feature.createform.CreateFormBaseFragment
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesFragment
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathFragment
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesFragment
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableFragment
import kotlinx.android.synthetic.main.fragment_general_info.*

class GeneralInfoFragment : CreateFormBaseFragment() {

    companion object {
        fun newInstance() = GeneralInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(CalamityInfoFragment.newInstance(), getString(R.string.calamity_info_title))
        pagerAdapter.addFragment(PopulationFragment.newInstance(), getString(R.string.population_title))
        pagerAdapter.addFragment(FamiliesFragment.newInstance(), getString(R.string.families_title))
        pagerAdapter.addFragment(VulnerableFragment.newInstance(), getString(R.string.vulnerable_title))
        pagerAdapter.addFragment(CasualtiesFragment.newInstance(), getString(R.string.casualties_title))
        pagerAdapter.addFragment(CauseOfDeathFragment.newInstance(), getString(R.string.cause_of_death_title))
        pagerAdapter.addFragment(InfrastructureDamageFragment.newInstance(), getString(R.string.infrastructure_damage_title))
        genInfoViewPager.adapter = pagerAdapter

        genInfoViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setSubtitle(pagerAdapter.getPageTitle(position).toString())
            }

            override fun onPageSelected(position: Int) {}
        })
    }

}
