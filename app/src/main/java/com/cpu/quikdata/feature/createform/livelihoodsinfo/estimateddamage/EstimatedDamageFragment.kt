package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_estimated_damage.*
import javax.inject.Inject

class EstimatedDamageFragment : BaseCollapsibleCreateFormFragment<EstimatedDamageAdapter, EstimatedDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = EstimatedDamageFragment()
    }

    @Inject lateinit var mEstimatedDamageAdapterFactory: EstimatedDamageAdapter.Factory

    private val mViewModel: EstimatedDamageViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(EstimatedDamageViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.livelihoodsInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estimated_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): EstimatedDamageAdapter {
        val adapter = mEstimatedDamageAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        estimatedDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.estimatedDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
