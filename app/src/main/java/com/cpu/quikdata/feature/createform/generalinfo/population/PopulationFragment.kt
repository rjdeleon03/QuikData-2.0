package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.graphics.Rect
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.core.view.children
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseFocusableFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_population.*
import kotlin.math.roundToInt
import android.view.ViewTreeObserver
import androidx.core.view.doOnNextLayout
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_vulnerable.*


class PopulationFragment : BaseFocusableFragment() {

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
        mAdapter = PopulationAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        populationRecyclerView.adapter = mAdapter

        populationRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(PopulationViewModel::class.java)

        var isInit = true
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            if (isInit) {
                populationRecyclerView.doOnNextLayout {
                    (populationRecyclerView.getChildAt(0) as CollapsibleContainer).expand(false)
                    isInit = false
                }
            }
            mAdapter.setRows(it)
        })
    }

}
