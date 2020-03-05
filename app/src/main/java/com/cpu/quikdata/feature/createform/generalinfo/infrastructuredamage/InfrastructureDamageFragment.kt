package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_infrastructure_damage.*

class InfrastructureDamageFragment : BaseCollapsibleCreateFormFragment<InfrastructureDamageAdapter, InfrastructureDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = InfrastructureDamageFragment()
    }

    private lateinit var mViewModel: InfrastructureDamageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infrastructure_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): InfrastructureDamageAdapter {
        val adapter = InfrastructureDamageAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        infrastructureDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProvider(this, mFactory).get(InfrastructureDamageViewModel::class.java)
        mViewModel.infrastructureDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
