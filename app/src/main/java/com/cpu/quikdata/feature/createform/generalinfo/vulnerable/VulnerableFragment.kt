package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_vulnerable.*

class VulnerableFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = VulnerableFragment()
    }

    private lateinit var mViewModel: VulnerableViewModel
    private lateinit var mAdapter: VulnerableAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vulnerable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = VulnerableAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        vulnerableRecyclerView.adapter = mAdapter
        vulnerableRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(VulnerableViewModel::class.java)
        mViewModel.vulnerable.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
