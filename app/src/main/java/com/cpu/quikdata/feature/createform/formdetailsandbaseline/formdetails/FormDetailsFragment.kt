package com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.formdetails.FormDetails
import kotlinx.android.synthetic.main.fragment_form_details.*

class FormDetailsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FormDetailsFragment()
    }

    private val mViewModel: FormDetailsViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(FormDetailsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.formDetailsAndBaselineComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form_details, container, false)
    }

    override fun onDestroyView() {
        val formDetails = FormDetails(
            assessmentDate = formDetailsAssessmentDateText.date,
            interviewer = formDetailsInterviewerText.text,
            interviewerContact = formDetailsInterviewerContactText.text,
            interviewee = formDetailsIntervieweeText.text,
            intervieweeContact = formDetailsIntervieweeContactText.text,
            sourcesOfInformation = formDetailsSourcesText.text
        )
        mViewModel.updateFormDetails(formDetails)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.formDetails.observe(viewLifecycleOwner, Observer {
            formDetailsAssessmentDateText.date = it.assessmentDate
            formDetailsInterviewerText.text = it.interviewer
            formDetailsInterviewerContactText.text = it.interviewerContact
            formDetailsIntervieweeText.text = it.interviewee
            formDetailsIntervieweeContactText.text = it.intervieweeContact
            formDetailsSourcesText.text = it.sourcesOfInformation
        })
    }

}
