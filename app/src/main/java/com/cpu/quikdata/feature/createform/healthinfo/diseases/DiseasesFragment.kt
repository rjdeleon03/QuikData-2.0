package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class DiseasesFragment : Fragment() {

    companion object {
        fun newInstance() = DiseasesFragment()
    }

    private lateinit var mViewModel: DiseasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diseases, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
