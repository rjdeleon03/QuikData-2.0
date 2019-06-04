package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import kotlinx.android.synthetic.main.fragment_site_info.*

class SiteInfoFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

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
        return inflater.inflate(R.layout.fragment_site_info, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateSiteInfo(SiteInfo(
            name = evacuationSiteInfoNameText.text,
            location = evacuationSiteInfoLocationText.text,
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
            evacuationSiteInfoNameText.text = it.name
            evacuationSiteInfoLocationText.text = it.location
            evacuationSiteInfoHaveMovedText.value = it.haveMoved
            evacuationSiteInfoLguDesignatedText.value = it.isLguDesignated
            evacuationSiteInfoDistanceText.text = it.distanceFromCommunity
            evacuationSiteInfoDateText.date = it.evacuationDate
            evacuationSiteInfoShelterSizeText.text = it.shelterSize
            evacuationSiteInfoHouseholdsText.number = it.householdsAndFamilies
        })
    }

}
