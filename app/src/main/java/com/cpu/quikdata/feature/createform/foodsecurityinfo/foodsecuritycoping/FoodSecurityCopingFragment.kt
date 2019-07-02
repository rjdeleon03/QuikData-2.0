package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import kotlinx.android.synthetic.main.fragment_food_security_coping.*

class FoodSecurityCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FoodSecurityCopingFragment()
    }

    private lateinit var mViewModel: FoodSecurityCopingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_coping, container, false)
    }

    override fun onDestroyView() {
        val foodSecurityCoping = FoodSecurityCoping(
            copingStrategies = foodSecurityCopingStrategiesText.text
        )
        mViewModel.updateFoodSecurityCoping(foodSecurityCoping)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(FoodSecurityCopingViewModel::class.java)
        mViewModel.foodSecurityCoping.observe(viewLifecycleOwner, Observer {
            foodSecurityCopingStrategiesText.text = it.copingStrategies
        })
    }

}
