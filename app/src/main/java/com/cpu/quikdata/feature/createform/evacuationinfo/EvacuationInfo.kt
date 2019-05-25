package com.cpu.quikdata.feature.createform.evacuationinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class EvacuationInfo : Fragment() {

    companion object {
        fun newInstance() = EvacuationInfo()
    }

    private lateinit var viewModel: EvacuationInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EvacuationInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}