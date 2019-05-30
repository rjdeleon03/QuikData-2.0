package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_house_damage.*

class HouseDamageFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = HouseDamageFragment()
    }

    private lateinit var mViewModel: HouseDamageViewModel
    private lateinit var mAdapter: HouseDamageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_damage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = HouseDamageAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        houseDamageRecyclerView.adapter = mAdapter
        houseDamageRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(HouseDamageViewModel::class.java)
        mViewModel.houseDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
