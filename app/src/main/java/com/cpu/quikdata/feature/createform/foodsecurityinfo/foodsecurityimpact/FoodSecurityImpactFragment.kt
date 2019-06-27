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
            UIJobScheduler.submitJob { foodSecurityImpactFoodAvailabilityText.value = it.hasFoodAvailabilityProblem }
            UIJobScheduler.submitJob { foodSecurityImpactFoodAvailabilityText.text = it.hasFoodAvailabilityProblemRemarks }
            UIJobScheduler.submitJob { foodSecurityImpactFoodAccessText.value = it.lacksFoodAccess }
            UIJobScheduler.submitJob { foodSecurityImpactFoodAccessText.text = it.lacksFoodAccessRemarks }
            UIJobScheduler.submitJob { foodSecurityImpactFoodConstraintsText.value = it.hasFoodAccessConstraints }
            UIJobScheduler.submitJob { foodSecurityImpactFoodConstraintsText.text = it.hasFoodAccessConstraintsRemarks }
            UIJobScheduler.submitJob { foodSecurityImpactFoodSourcesText.value = it.hasOtherFoodSources }
            UIJobScheduler.submitJob { foodSecurityImpactFoodSourcesText.text = it.hasOtherFoodSourcesRemarks }
            UIJobScheduler.submitJob { foodSecurityImpactEatingTimesBeforeText.text = it.eatingTimesBeforeEmergency }
            UIJobScheduler.submitJob { foodSecurityImpactEatingTimesAfterText.text = it.eatingTimesAfterEmergency }
            UIJobScheduler.submitJob { foodSecurityImpactFoodNeedsBeforeText.text = it.meetsFoodNeedsBeforeEmergency }
            UIJobScheduler.submitJob { foodSecurityImpactFoodNeedsAfterText.text = it.meetsFoodNeedsAfterEmergency }
            UIJobScheduler.submitJob { foodSecurityImpactFoodProductionText.text = it.foodProductionChange }
            UIJobScheduler.submitJob { foodSecurityImpactFoodRationText.text = it.nextFoodRation }
        })
    }

}
