package com.cpu.quikdata.feature.createform.watersanitationinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class WaterSanitationInfoFragment : Fragment() {

    companion object {
        fun newInstance() = WaterSanitationInfoFragment()
    }

    private lateinit var viewModel: WaterSanitationInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_water_sanitation_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WaterSanitationInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
