package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import kotlinx.android.synthetic.main.fragment_food_security_impact.*

class FoodSecurityImpactFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FoodSecurityImpactFragment()
    }

    private lateinit var mViewModel: FoodSecurityImpactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_impact, container, false)
    }

    override fun onDestroyView() {
        val foodSecurityImpact = FoodSecurityImpact(
            hasFoodAvailabilityProblem = foodSecurityImpactFoodAvailabilityText.value,
            hasFoodAvailabilityProblemRemarks = foodSecurityImpactFoodAvailabilityText.text,
            lacksFoodAccess = foodSecurityImpactFoodAccessText.value,
            lacksFoodAccessRemarks = foodSecurityImpactFoodAccessText.text,
            hasFoodAccessConstraints = foodSecurityImpactFoodConstraintsText.value,
            hasFoodAccessConstraintsRemarks = foodSecurityImpactFoodConstraintsText.text,
            hasOtherFoodSources = foodSecurityImpactFoodSourcesText.value,
            hasOtherFoodSourcesRemarks = foodSecurityImpactFoodSourcesText.text,
            eatingTimesBeforeEmergency = foodSecurityImpactEatingTimesBeforeText.text,
            eatingTimesAfterEmergency = foodSecurityImpactEatingTimesAfterText.text,
            meetsFoodNeedsBeforeEmergency = foodSecurityImpactFoodNeedsBeforeText.text,
            meetsFoodNeedsAfterEmergency = foodSecurityImpactFoodNeedsAfterText.text,
            foodProductionChange = foodSecurityImpactFoodProductionText.text,
            nextFoodRation = foodSecurityImpactFoodRationText.text
        )
        mViewModel.updateFoodSecurityImpact(foodSecurityImpact)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(FoodSecurityImpactViewModel::class.java)
        mViewModel.foodSecurityImpact.observe(viewLifecycleOwner, Observer {
            foodSecurityImpactFoodAvailabilityText.value = it.hasFoodAvailabilityProblem
            foodSecurityImpactFoodAvailabilityText.text = it.hasFoodAvailabilityProblemRemarks
            foodSecurityImpactFoodAccessText.value = it.lacksFoodAccess
            foodSecurityImpactFoodAccessText.text = it.lacksFoodAccessRemarks
            foodSecurityImpactFoodConstraintsText.value = it.hasFoodAccessConstraints
            foodSecurityImpactFoodConstraintsText.text = it.hasFoodAccessConstraintsRemarks
            foodSecurityImpactFoodSourcesText.value = it.hasOtherFoodSources
            foodSecurityImpactFoodSourcesText.text = it.hasOtherFoodSourcesRemarks
            foodSecurityImpactEatingTimesBeforeText.text = it.eatingTimesBeforeEmergency
            foodSecurityImpactEatingTimesAfterText.text = it.eatingTimesAfterEmergency
            foodSecurityImpactFoodNeedsBeforeText.text = it.meetsFoodNeedsBeforeEmergency
            foodSecurityImpactFoodNeedsAfterText.text = it.meetsFoodNeedsAfterEmergency
            foodSecurityImpactFoodProductionText.text = it.foodProductionChange
            foodSecurityImpactFoodRationText.text = it.nextFoodRation
        })
    }

}
