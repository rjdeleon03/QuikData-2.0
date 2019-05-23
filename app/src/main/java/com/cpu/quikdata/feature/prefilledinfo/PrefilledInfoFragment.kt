package com.cpu.quikdata.feature.prefilledinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class PrefilledInfoFragment : Fragment() {

    companion object {
        fun newInstance() = PrefilledInfoFragment()
    }

    private lateinit var viewModel: PrefilledInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prefilled_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PrefilledInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
