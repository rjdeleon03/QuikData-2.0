package com.cpu.quikdata.feature.newforms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cpu.quikdata.R

class NewFormsFragment : Fragment() {

    companion object {
        fun newInstance() = NewFormsFragment()
    }

    private lateinit var viewModel: NewFormsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_forms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewFormsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
