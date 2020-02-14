package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_vulnerable.*
import javax.inject.Inject

class VulnerableFragment : BaseCollapsibleCreateFormFragment<VulnerableAdapter, VulnerableAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = VulnerableFragment()
    }

    @Inject
    lateinit var mViewModel: VulnerableViewModel

    @Inject
    lateinit var mAdapterFactory: VulnerableAdapter.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vulnerable, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): VulnerableAdapter {
        val adapter = mAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        vulnerableRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.vulnerable.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
