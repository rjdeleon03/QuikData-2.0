package com.cpu.quikdata.feature.createform.casestories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.feature.createform.CreateFormActivity

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
        super.onDestroyView()
        (activity!! as CreateFormActivity).setSubtitle()
    }

    fun setSubtitle(subtitle: CharSequence?) {
        (activity!! as CreateFormActivity).setSubtitle(subtitle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(CaseStoriesViewModel::class.java)
    }

}
