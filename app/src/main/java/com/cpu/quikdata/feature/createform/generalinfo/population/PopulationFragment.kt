package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_population.*

class PopulationFragment : Fragment() {

    companion object {
        fun newInstance() = PopulationFragment()
    }

    private lateinit var mParentViewModel: CreateFormViewModel
    private lateinit var mViewModel: PopulationViewModel
    private lateinit var mAdapter: PopulationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_population, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = PopulationAdapter(context!!)
        populationRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(PopulationViewModel::class.java)
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            if (it.populationRows != null) mAdapter.setRows(it.populationRows!!)
        })
    }

}
