package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_population.*


class PopulationFragment : BaseCollapsibleCreateFormFragment<PopulationAdapter, PopulationAdapter.ViewHolder>() {

    companion object {
        fun newInstance() = PopulationFragment()
    }

    private lateinit var mViewModel: PopulationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_population, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): PopulationAdapter {
        val adapter = PopulationAdapter(requireContext(), {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        populationRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProvider(this, mFactory).get(PopulationViewModel::class.java)
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
