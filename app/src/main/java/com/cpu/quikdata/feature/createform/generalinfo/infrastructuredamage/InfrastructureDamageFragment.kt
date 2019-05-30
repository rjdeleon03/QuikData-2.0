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

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(InfrastructureDamageViewModel::class.java)

        var isInit = true
        mViewModel.infrastructureDamage.observe(viewLifecycleOwner, Observer {
            if (isInit) {
                infrastructureDamageRecyclerView.doOnNextLayout {
                    (infrastructureDamageRecyclerView.getChildAt(0) as CollapsibleContainer).expand(false)
                    isInit = false
                }
            }
            mAdapter.setRows(it)
        })
    }

}
