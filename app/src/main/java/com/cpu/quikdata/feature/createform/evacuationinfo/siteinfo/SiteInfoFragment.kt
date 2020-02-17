package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.common.EvacuationCategories
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment.Companion.EVACUATION_ID_KEY
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_evacuation_site_info.*
import javax.inject.Inject

class SiteInfoFragment : DaggerFragment() {

    companion object {

        @JvmStatic
        fun start(evacuationId: String): SiteInfoFragment {
            val fragment = SiteInfoFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var mViewModel: SiteInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_site_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        evacuationSiteInfoTypeText.items = EvacuationCategories.getStringIdList()
    }

    override fun onDestroyView() {
        mViewModel.updateSiteInfo(SiteInfo(
            name = evacuationSiteInfoNameText.text,
            location = evacuationSiteInfoLocationText.text,
            type = evacuationSiteInfoTypeText.selectedIndex,
            haveMoved = evacuationSiteInfoHaveMovedText.value,
            isLguDesignated = evacuationSiteInfoLguDesignatedText.value,
            distanceFromCommunity = evacuationSiteInfoDistanceText.text,
            evacuationDate = evacuationSiteInfoDateText.date,
            shelterSize = evacuationSiteInfoShelterSizeText.text,
            householdsAndFamilies = evacuationSiteInfoHouseholdsText.number
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.siteInfo.observe(viewLifecycleOwner, Observer {
            evacuationSiteInfoNameText.text = it.name
            evacuationSiteInfoLocationText.text = it.location
            evacuationSiteInfoTypeText.selectedIndex = it.type
            evacuationSiteInfoHaveMovedText.value = it.haveMoved
            evacuationSiteInfoLguDesignatedText.value = it.isLguDesignated
            evacuationSiteInfoDistanceText.text = it.distanceFromCommunity
            evacuationSiteInfoDateText.date = it.evacuationDate
            evacuationSiteInfoShelterSizeText.text = it.shelterSize
            evacuationSiteInfoHouseholdsText.number = it.householdsAndFamilies
        })
    }

}
