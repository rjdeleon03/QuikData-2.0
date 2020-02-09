package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_estimated_damage.*

class EstimatedDamageFragment : BaseCollapsibleCreateFormFragment<EstimatedDamageAdapter, EstimatedDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = EstimatedDamageFragment()
    }

    private lateinit var mViewModel: EstimatedDamageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estimated_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): EstimatedDamageAdapter {
        val adapter = EstimatedDamageAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        estimatedDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProvider(this, mFactory).get(EstimatedDamageViewModel::class.java)
        mViewModel.estimatedDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
