package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.base.BaseFocusableFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_calamity_info.*

class CalamityInfoFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = CalamityInfoFragment()
    }

    private lateinit var mViewModel: CalamityInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calamity_info, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateCalamityInfo(CalamityInfo(
            calamityType = calamityTypeText.text,
            occurrenceDate = calamityDateText.date,
            eventDescription = calamityDescText.text,
            affectedAreaDescription = calamityAreaText.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(CalamityInfoViewModel::class.java)
        mViewModel.calamityInfo.observe(viewLifecycleOwner, Observer {
            calamityTypeText.text = it.calamityType
            calamityDateText.date = it.occurrenceDate
            calamityDescText.text = it.eventDescription
            calamityAreaText.text = it.affectedAreaDescription
        })
    }

}
