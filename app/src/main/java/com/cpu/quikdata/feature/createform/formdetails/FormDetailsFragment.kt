package com.cpu.quikdata.feature.createform.formdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import com.cpu.quikdata.feature.createform.generalinfo.GeneralInfoFragment
import kotlinx.android.synthetic.main.fragment_form_details.*

class FormDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = FormDetailsFragment()
    }

    private lateinit var mParentViewModel: CreateFormViewModel
    private lateinit var mViewModel: FormDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)
        mViewModel = ViewModelProviders.of(this).get(FormDetailsViewModel::class.java)
        mViewModel.formDetails.observe(viewLifecycleOwner, Observer {
            formDetailsAssessmentDateText.date = it.assessmentDate
        })
    }

}
