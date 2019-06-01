package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import kotlinx.android.synthetic.main.fragment_food_security_gaps.*

class FoodSecurityGapsFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = FoodSecurityGapsFragment()
    }

    private lateinit var mViewModel: FoodSecurityGapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_gaps, container, false)
    }

    override fun onDestroyView() {
        val foodSecurityGaps = FoodSecurityGaps(
            assistanceAppropriate = foodSecurityGapsAssistanceAppropriateText.text,
            assistanceEnough = foodSecurityGapsAssistanceEnoughText.text,
            assistanceEqualAccess = foodSecurityGapsAssistanceEqualAccessText.text,
            specificNeedsMet = foodSecurityGapsSpecificNeedsMetText.text
        )
        mViewModel.updateFoodSecurityGaps(foodSecurityGaps)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(FoodSecurityGapsViewModel::class.java)
        mViewModel.foodSecurityGaps.observe(viewLifecycleOwner, Observer {
            foodSecurityGapsAssistanceAppropriateText.text = it.assistanceAppropriate
            foodSecurityGapsAssistanceEnoughText.text = it.assistanceEnough
            foodSecurityGapsAssistanceEqualAccessText.text = it.assistanceEqualAccess
            foodSecurityGapsSpecificNeedsMetText.text = it.specificNeedsMet
        })
    }

}
