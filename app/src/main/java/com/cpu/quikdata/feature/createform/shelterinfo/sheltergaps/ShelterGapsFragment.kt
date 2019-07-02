package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import kotlinx.android.synthetic.main.fragment_shelter_gaps.*

class ShelterGapsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ShelterGapsFragment()
    }

    private lateinit var mViewModel: ShelterGapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_gaps, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateShelterGaps(ShelterGaps(
            cubicles = shelterGapsCubicles.text,
            culturalPracticeAssistance = shelterGapsCulturalPracticeAssistance.text,
            shelterAppropriate = shelterGapsShelterAppropriate.text,
            servicesAccess = shelterGapsServicesAccess.text,
            anyAbleBodied = shelterGapsAnyAbleBodied.text,
            gbvReferralPathway = shelterGapsGbvReferralPathway.text,
            gbvProtectionServices = shelterGapsGbvProtectionServices.text,
            gbvProtectionFocalPoint = shelterGapsGbvProtectionFocalPoint.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(ShelterGapsViewModel::class.java)
        mViewModel.shelterGaps.observe(viewLifecycleOwner, Observer {
            shelterGapsCubicles.text = it.cubicles
            shelterGapsCulturalPracticeAssistance.text = it.culturalPracticeAssistance
            shelterGapsShelterAppropriate.text = it.shelterAppropriate
            shelterGapsServicesAccess.text = it.servicesAccess
            shelterGapsAnyAbleBodied.text = it.anyAbleBodied
            shelterGapsGbvReferralPathway.text = it.gbvReferralPathway
            shelterGapsGbvProtectionServices.text = it.gbvProtectionServices
            shelterGapsGbvProtectionFocalPoint.text = it.gbvProtectionFocalPoint
        })
    }

}
