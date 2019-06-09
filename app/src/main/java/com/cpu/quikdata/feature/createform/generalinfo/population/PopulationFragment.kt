package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.fragment_population.*
import com.cpu.quikdata.base.BaseCreateFormFragment


class PopulationFragment : BaseCreateFormFragment() {

    companion object {
        private const val EXPANDED_ITEM_INDEX_KEY = "EXPANDED_ITEM_INDEX_KEY"

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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(EXPANDED_ITEM_INDEX_KEY, mAdapter.expandedItemIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var expandedItemIndex = 0
        if (savedInstanceState != null) {
            expandedItemIndex = savedInstanceState.getInt(EXPANDED_ITEM_INDEX_KEY, 0)
        }
        mAdapter = PopulationAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        populationRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(PopulationViewModel::class.java)
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
