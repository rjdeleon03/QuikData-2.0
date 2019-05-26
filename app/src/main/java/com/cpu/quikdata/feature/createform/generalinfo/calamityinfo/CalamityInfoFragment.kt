package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.feature.createform.CreateFormViewModel

class CalamityInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CalamityInfoFragment()
    }

    private lateinit var mParentViewModel: CreateFormViewModel
    private lateinit var mViewModel: CalamityInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calamity_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(CalamityInfoViewModel::class.java)
    }

}
