package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_house_damage.*

class HouseDamageFragment : BaseCollapsibleCreateFormFragment<HouseDamageAdapter, HouseDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = HouseDamageFragment()
    }

    private lateinit var mViewModel: HouseDamageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): HouseDamageAdapter {
        val adapter = HouseDamageAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        houseDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(HouseDamageViewModel::class.java)
        mViewModel.houseDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
