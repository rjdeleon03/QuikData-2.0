package com.cpu.quikdata.feature.createform.livelihoodsinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class LivelihoodsInfoFragment : Fragment() {

    companion object {
        fun newInstance() = LivelihoodsInfoFragment()
    }

    private lateinit var viewModel: LivelihoodsInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LivelihoodsInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
