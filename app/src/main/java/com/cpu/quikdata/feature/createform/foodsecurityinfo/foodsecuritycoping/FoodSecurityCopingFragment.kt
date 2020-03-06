package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import kotlinx.android.synthetic.main.fragment_food_security_coping.*

class FoodSecurityCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FoodSecurityCopingFragment()
    }

    private val mViewModel: FoodSecurityCopingViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(FoodSecurityCopingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.foodSecurityInfoComponent().create().inject(this)
    }

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

        mViewModel.foodSecurityCoping.observe(viewLifecycleOwner, Observer {
            foodSecurityCopingStrategiesText.text = it.copingStrategies
        })
    }

}
