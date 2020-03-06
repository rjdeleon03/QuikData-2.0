package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_infrastructure_damage.*
import javax.inject.Inject

class InfrastructureDamageFragment : BaseCollapsibleCreateFormFragment<InfrastructureDamageAdapter, InfrastructureDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = InfrastructureDamageFragment()
    }

    @Inject lateinit var mInfrastructureDamageAdapterFactory: InfrastructureDamageAdapter.Factory

    private val mViewModel: InfrastructureDamageViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(InfrastructureDamageViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.generalInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infrastructure_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): InfrastructureDamageAdapter {
        val adapter = mInfrastructureDamageAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        infrastructureDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.infrastructureDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
