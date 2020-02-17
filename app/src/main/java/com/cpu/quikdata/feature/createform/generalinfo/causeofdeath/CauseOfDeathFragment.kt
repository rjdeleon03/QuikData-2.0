package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_cause_of_death.*
import javax.inject.Inject

class CauseOfDeathFragment : BaseCollapsibleCreateFormFragment<CauseOfDeathAdapter, CauseOfDeathAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun start() = CauseOfDeathFragment()
    }

    @Inject
    lateinit var mViewModel: CauseOfDeathViewModel

    @Inject
    lateinit var mAdapterFactory: CauseOfDeathAdapter.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cause_of_death, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): CauseOfDeathAdapter {
        val adapter = mAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        causeOfDeathRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
