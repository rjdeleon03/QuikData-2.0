package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import kotlinx.android.synthetic.main.fragment_calamity_info.*

class CalamityInfoFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CalamityInfoFragment()
    }

    private val mViewModel: CalamityInfoViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(CalamityInfoViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.generalInfoComponent().create().inject(this)
    }

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

        mViewModel.calamityInfo.observe(viewLifecycleOwner, Observer {
            calamityTypeText.text = it.calamityType
            calamityDateText.date = it.occurrenceDate
            calamityDescText.text = it.eventDescription
            calamityAreaText.text = it.affectedAreaDescription
        })
    }

}
