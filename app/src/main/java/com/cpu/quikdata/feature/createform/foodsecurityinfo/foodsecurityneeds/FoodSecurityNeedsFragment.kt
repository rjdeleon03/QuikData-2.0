package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import kotlinx.android.synthetic.main.fragment_food_security_needs.*
import javax.inject.Inject

class FoodSecurityNeedsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun start() = FoodSecurityNeedsFragment()
    }

    @Inject
    lateinit var mViewModel: FoodSecurityNeedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_needs, container, false)
    }

    override fun onDestroyView() {
        val foodSecurityNeeds = FoodSecurityNeeds(
            foodGapAssistance = foodSecurityNeedsFoodGapText.text,
            familiesInNeed = foodSecurityNeedsFamiliesInNeedText.text
        )
        mViewModel.updateFoodSecurityNeeds(foodSecurityNeeds)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.foodSecurityNeeds.observe(viewLifecycleOwner, Observer {
            foodSecurityNeedsFoodGapText.text = it.foodGapAssistance
            foodSecurityNeedsFamiliesInNeedText.text = it.familiesInNeed
        })
    }

}
