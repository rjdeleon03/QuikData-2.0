package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_psychosocial.*
import javax.inject.Inject

class PsychosocialFragment : BaseCollapsibleCreateFormFragment<PsychosocialAdapter, PsychosocialAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = PsychosocialFragment()
    }

    @Inject lateinit var psychosocialAdapterFactory: PsychosocialAdapter.Factory

    private val mViewModel: PsychosocialViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(PsychosocialViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_psychosocial, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): PsychosocialAdapter {
        val adapter = psychosocialAdapterFactory.create({
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
