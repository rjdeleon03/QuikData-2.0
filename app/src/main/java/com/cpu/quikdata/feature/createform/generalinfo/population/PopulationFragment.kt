package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.fragment_population.*
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.setupTapToExpand


class PopulationFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = PopulationFragment()
    }

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
        mAdapter = PopulationAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        populationRecyclerView.adapter = mAdapter
        populationRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(PopulationViewModel::class.java)
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
