package com.cpu.quikdata.feature.createform.foodsecurityinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class FoodSecurityInfoFragment : Fragment() {

    companion object {
        fun newInstance() = FoodSecurityInfoFragment()
    }

    private lateinit var viewModel: FoodSecurityInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodSecurityInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
