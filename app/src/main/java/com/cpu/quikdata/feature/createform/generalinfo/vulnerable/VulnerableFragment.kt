package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_vulnerable.*

class VulnerableFragment : BaseCollapsibleCreateFormFragment<VulnerableAdapter, VulnerableAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = VulnerableFragment()
    }

    private lateinit var mViewModel: VulnerableViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vulnerable, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): VulnerableAdapter {
        val adapter = VulnerableAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        vulnerableRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(VulnerableViewModel::class.java)
        mViewModel.vulnerable.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
