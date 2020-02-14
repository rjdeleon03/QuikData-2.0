package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_psychosocial.*
import javax.inject.Inject

class PsychosocialFragment : BaseCollapsibleCreateFormFragment<PsychosocialAdapter, PsychosocialAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = PsychosocialFragment()
    }

    @Inject
    lateinit var mViewModel: PsychosocialViewModel

    @Inject
    lateinit var mAdapterFactory: PsychosocialAdapter.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_psychosocial, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): PsychosocialAdapter {
        val adapter = mAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        psychosocialRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.psychosocial.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
