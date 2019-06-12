package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import kotlinx.android.synthetic.main.fragment_wash_gaps.*

class WashGapsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = WashGapsFragment()
    }

    private lateinit var mViewModel: WashGapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wash_gaps, container, false)
    }

    override fun onDestroyView() {
        val washGaps = WashGaps(
            assistanceEnough = washGapsAssistanceEnoughText.text,
            assistanceRelevant = washGapsAssistanceRelevantText.text,
            waterSourceAccessible = washWaterSourceAccessibleText.text,
            reliefConsultation = washGapsReliefConsultationText.text,
            wasteManagement = washGapsWasteManagementText.text,
            attitudes = washGapsAttitudesText.text,
            supportMechanisms = washGapsSupportMechanisms.text,
            womenParticipation = washGapsWomenParticipationText.text
        )
        mViewModel.updateWashGaps(washGaps)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(WashGapsViewModel::class.java)
        mViewModel.washGaps.observe(viewLifecycleOwner, Observer {
            washGapsAssistanceEnoughText.text = it.assistanceEnough
            washGapsAssistanceRelevantText.text = it.assistanceRelevant
            washWaterSourceAccessibleText.text = it.waterSourceAccessible
            washGapsReliefConsultationText.text = it.reliefConsultation
            washGapsWasteManagementText.text = it.wasteManagement
            washGapsAttitudesText.text = it.attitudes
            washGapsSupportMechanisms.text = it.supportMechanisms
            washGapsWomenParticipationText.text = it.womenParticipation
        })
    }

}
