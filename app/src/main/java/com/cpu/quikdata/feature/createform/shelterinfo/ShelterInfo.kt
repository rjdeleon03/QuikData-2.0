package com.cpu.quikdata.feature.createform.shelterinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class ShelterInfo : Fragment() {

    companion object {
        fun newInstance() = ShelterInfo()
    }

    private lateinit var viewModel: ShelterInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShelterInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
