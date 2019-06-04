package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import kotlinx.android.synthetic.main.fragment_estimated_damage.*

class EstimatedDamageFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = EstimatedDamageFragment()
    }

    private lateinit var mViewModel: EstimatedDamageViewModel
    private lateinit var mAdapter: EstimatedDamageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estimated_damage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = EstimatedDamageAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        estimatedDamageRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(EstimatedDamageViewModel::class.java)
        mViewModel.estimatedDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
