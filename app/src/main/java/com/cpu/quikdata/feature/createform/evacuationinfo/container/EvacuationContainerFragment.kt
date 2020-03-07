package com.cpu.quikdata.feature.createform.evacuationinfo.container


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.CustomPagerAdapter
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.setupViewPager
import com.cpu.quikdata.di.createform.evacuationinfo.evacuationitem.EvacuationItemComponent
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoFragment
import kotlinx.android.synthetic.main.fragment_evacuation_container.*

class EvacuationContainerFragment : BaseCreateFormFragment() {

    val args: EvacuationContainerFragmentArgs by navArgs()

    val evacuationItemComponent: EvacuationItemComponent
        get() = mEvacuationItemComponent

    private lateinit var mEvacuationItemComponent: EvacuationItemComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mEvacuationItemComponent = mCreateFormComponent.evacuationInfoComponent()
            .create().evacuationItemComponent().create(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evacuation_container, container, false)
    }

    override fun onResume() {
        super.onResume()
        val editMode = args.isEditMode
        val parentActivity = activity as CreateFormActivity
        if (editMode) {
            parentActivity.setToolbarTitle(getString(R.string.evacuation_edit_title))
        } else {
            parentActivity.setToolbarTitle(getString(R.string.evacuation_create_title))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupClipping(evacuationInfoViewPager)

        val evacuationId = args.evacuationId
        val pagerAdapter = CustomPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(SiteInfoFragment.newInstance(), getString(R.string.evacuation_site_info_title))
        pagerAdapter.addFragment(EvacuationAgeFragment.newInstance(), getString(R.string.evacuation_age_title))
        pagerAdapter.addFragment(EvacuationFacilitiesFragment.newInstance(evacuationId), getString(R.string.evacuation_facilities_title))
        pagerAdapter.addFragment(EvacuationWashFragment.newInstance(evacuationId), getString(R.string.evacuation_wash_title))
        pagerAdapter.addFragment(EvacuationProtectionFragment.newInstance(evacuationId), getString(R.string.evacuation_protection_title))
        pagerAdapter.addFragment(EvacuationCopingFragment.newInstance(evacuationId), getString(R.string.evacuation_coping_title))
        evacuationInfoViewPager.setupViewPager(pagerAdapter) { (activity as CreateFormActivity).setSubtitle(it) }
    }
}
