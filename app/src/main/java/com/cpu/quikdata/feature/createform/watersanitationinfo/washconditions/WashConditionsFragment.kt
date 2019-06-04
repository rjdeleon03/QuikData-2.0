package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import com.cpu.quikdata.dialog.InfoDialogFragment
import kotlinx.android.synthetic.main.fragment_wash_conditions.*

class WashConditionsFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = WashConditionsFragment()
    }

    private lateinit var mViewModel: WashConditionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wash_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        washConditionsDrinkingWaterText.onInfoClickListener = {
            val dialog = InfoDialogFragment.newInstance(R.string.wash_conditions_number_of_water_points, R.string.wash_conditions_water_potable)
            dialog.show(childFragmentManager, InfoDialogFragment.TAG)
        }
    }

    override fun onDestroyView() {
        val washConditions = WashConditions(
            drinkingWaterLevel = washConditionsDrinkingWaterText.value,
            drinkingWaterRemarks = washConditionsDrinkingWaterText.text,
            bathingWaterLevel = washConditionsBathingWaterText.value,
            bathingWaterRemarks = washConditionsBathingWaterText.text,
            numberOfWaterPoints = washConditionsNumberOfWaterPointsText.text,
            waterPotable = washConditionsWaterPotableText.text,
            fetchingWaterTime = washConditionsFetchingWaterTimeText.text,
            waterSourceOwner = washConditionsWaterSourceOwnerText.text,
            waterCost = washConditionsWaterCostText.text,
            transportationCost = washConditionsTransportationCostText.text,
            timesOfNoWater = washConditionsTimesOfNoWaterText.text,
            handWashingFacilities = washConditionsHandWashingFacilitiesText.text,
            garbageDisposalFacilities = washConditionsGarbageDisposalFacilitiesText.text,
            wasteSegregation = washConditionsWasteSegregationText.text,
            menstruationManagement = washConditionsMenstruationManagementText.text,
            napkinsDisposal = washConditionsNapkinsDisposalText.text,
            diapersDisposal = washConditionsDiapersDisposalText.text,
            defecationPractices = washConditionsDefecationPracticesText.text,
            toiletFacilities = washConditionsToiletFacilitiesText.text,
            toiletConditions = washConditionsToiletConditionsText.text,
            defecationThreat = washConditionsDefecationThreatText.text,
            existingFacilities = washConditionsExistingFacilitiesText.text,
            securityIssues = washConditionsSecurityIssuesText.text,
            toiletSegregation = washConditionsToiletSegregationText.text,
            toiletAccessibility = washConditionsToiletAccessibilityText.text
        )
        mViewModel.updateWashConditions(washConditions)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(WashConditionsViewModel::class.java)
        mViewModel.washConditions.observe(viewLifecycleOwner, Observer {
            washConditionsDrinkingWaterText.value = it.drinkingWaterLevel
            washConditionsDrinkingWaterText.text = it.drinkingWaterRemarks
            washConditionsBathingWaterText.value = it.bathingWaterLevel
            washConditionsBathingWaterText.text = it.bathingWaterRemarks
            washConditionsNumberOfWaterPointsText.text = it.numberOfWaterPoints
            washConditionsWaterPotableText.text = it.waterPotable
            washConditionsFetchingWaterTimeText.text = it.fetchingWaterTime
            washConditionsWaterSourceOwnerText.text = it.waterSourceOwner
            washConditionsWaterCostText.text = it.waterCost
            washConditionsTransportationCostText.text = it.transportationCost
            washConditionsTimesOfNoWaterText.text = it.timesOfNoWater
            washConditionsHandWashingFacilitiesText.text = it.handWashingFacilities
            washConditionsGarbageDisposalFacilitiesText.text = it.garbageDisposalFacilities
            washConditionsWasteSegregationText.text = it.wasteSegregation
            washConditionsMenstruationManagementText.text = it.menstruationManagement
            washConditionsNapkinsDisposalText.text = it.napkinsDisposal
            washConditionsDiapersDisposalText.text = it.diapersDisposal
            washConditionsDefecationPracticesText.text = it.defecationPractices
            washConditionsToiletFacilitiesText.text = it.toiletFacilities
            washConditionsToiletConditionsText.text = it.toiletConditions
            washConditionsDefecationThreatText.text = it.defecationThreat
            washConditionsExistingFacilitiesText.text = it.existingFacilities
            washConditionsSecurityIssuesText.text = it.securityIssues
            washConditionsToiletSegregationText.text = it.toiletSegregation
            washConditionsToiletAccessibilityText.text = it.toiletAccessibility
        })
    }

}
