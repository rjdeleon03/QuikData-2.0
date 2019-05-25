package com.cpu.quikdata.feature.createform.healthinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class HealthInfoFragment : Fragment() {

    companion object {
        fun newInstance() = HealthInfoFragment()
    }

    private lateinit var viewModel: HealthInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HealthInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
