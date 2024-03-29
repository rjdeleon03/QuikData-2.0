package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.common.setupTapToExpand
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_infrastructure_damage.*

class InfrastructureDamageFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = InfrastructureDamageFragment()
    }

    private lateinit var mViewModel: InfrastructureDamageViewModel
    private lateinit var mAdapter: InfrastructureDamageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infrastructure_damage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = InfrastructureDamageAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        infrastructureDamageRecyclerView.adapter = mAdapter
        infrastructureDamageRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(InfrastructureDamageViewModel::class.java)
        mViewModel.infrastructureDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
