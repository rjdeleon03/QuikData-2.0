package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.EvacuationCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import kotlinx.android.synthetic.main.fragment_evacuation_site_info.*

class SiteInfoFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): SiteInfoFragment {
            val fragment = SiteInfoFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: SiteInfoViewModel

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

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(activity!!.application, evacuationId)
        mViewModel = ViewModelProviders.of(this, factory).get(SiteInfoViewModel::class.java)
        mViewModel.siteInfo.observe(viewLifecycleOwner, Observer {
            UIJobScheduler.submitJob { evacuationSiteInfoNameText.text = it.name }
            UIJobScheduler.submitJob { evacuationSiteInfoLocationText.text = it.location }
            UIJobScheduler.submitJob { evacuationSiteInfoTypeText.selectedIndex = it.type }
            UIJobScheduler.submitJob { evacuationSiteInfoHaveMovedText.value = it.haveMoved }
            UIJobScheduler.submitJob { evacuationSiteInfoLguDesignatedText.value = it.isLguDesignated }
            UIJobScheduler.submitJob { evacuationSiteInfoDistanceText.text = it.distanceFromCommunity }
            UIJobScheduler.submitJob { evacuationSiteInfoDateText.date = it.evacuationDate }
            UIJobScheduler.submitJob { evacuationSiteInfoShelterSizeText.text = it.shelterSize }
            UIJobScheduler.submitJob { evacuationSiteInfoHouseholdsText.number = it.householdsAndFamilies }
        })
    }

}
