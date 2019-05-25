package com.cpu.quikdata.feature.createform.casestories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class CaseStoriesFragment : Fragment() {

    companion object {
        fun newInstance() = CaseStoriesFragment()
    }

    private lateinit var viewModel: CaseStoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_case_stories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaseStoriesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
