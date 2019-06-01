package com.cpu.quikdata.feature.createform.watersanitationinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormSectionFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingFragment
import kotlinx.android.synthetic.main.fragment_water_sanitation_info.*

class WaterSanitationInfoFragment : BaseCreateFormSectionFragment() {

    companion object {
        fun newInstance() = WaterSanitationInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_water_sanitation_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(WashCopingFragment.newInstance(), getString(R.string.wash_coping_title))
        washViewPager.setupViewPager(pagerAdapter) { setSubtitle(it) }
    }

}
