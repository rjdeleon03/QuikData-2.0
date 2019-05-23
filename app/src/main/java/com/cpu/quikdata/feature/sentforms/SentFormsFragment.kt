package com.cpu.quikdata.feature.sentforms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class SentFormsFragment : Fragment() {

    companion object {
        fun newInstance() = SentFormsFragment()
    }

    private lateinit var viewModel: SentFormsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sent_forms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SentFormsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
