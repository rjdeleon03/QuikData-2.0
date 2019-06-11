package com.cpu.quikdata.feature.createform.casestories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.casestories.CaseStories
import kotlinx.android.synthetic.main.fragment_case_stories.*

class CaseStoriesFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = CaseStoriesFragment()
    }

    private lateinit var mViewModel: CaseStoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_case_stories, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateCaseStoriesText(CaseStories(text = caseStoriesText.text))
        super.onDestroyView()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CaseStoriesViewModel::class.java)
        mViewModel.caseStories.observe(viewLifecycleOwner, Observer {
            caseStoriesText.text = it.root!!.text
        })
    }

}
