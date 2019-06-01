package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_psychosocial.*

class PsychosocialFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = PsychosocialFragment()
    }

    private lateinit var mViewModel: PsychosocialViewModel
    private lateinit var mAdapter: PsychosocialAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_psychosocial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = PsychosocialAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        psychosocialRecyclerView.adapter = mAdapter
        psychosocialRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        mViewModel = ViewModelProviders.of(this, mFactory).get(PsychosocialViewModel::class.java)
        mViewModel.psychosocial.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
