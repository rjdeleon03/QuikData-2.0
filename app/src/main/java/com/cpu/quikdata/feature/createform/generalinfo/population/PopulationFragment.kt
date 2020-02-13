package com.cpu.quikdata.feature.createform.generalinfo.population

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_population.*
import javax.inject.Inject


class PopulationFragment : BaseCollapsibleCreateFormFragment<PopulationAdapter, PopulationAdapter.ViewHolder>() {

    companion object {
        fun newInstance() = PopulationFragment()
    }

    @Inject
    lateinit var mViewModel: PopulationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_population, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): PopulationAdapter {
        val adapter = PopulationAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        populationRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.population.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
