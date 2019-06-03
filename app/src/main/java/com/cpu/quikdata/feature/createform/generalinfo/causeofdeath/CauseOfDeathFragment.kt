package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_cause_of_death.*

class CauseOfDeathFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = CauseOfDeathFragment()
    }

    private lateinit var mViewModel: CauseOfDeathViewModel
    private lateinit var mAdapter: CauseOfDeathAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cause_of_death, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CauseOfDeathAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        causeOfDeathRecyclerView.adapter = mAdapter
        causeOfDeathRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CauseOfDeathViewModel::class.java)
        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
