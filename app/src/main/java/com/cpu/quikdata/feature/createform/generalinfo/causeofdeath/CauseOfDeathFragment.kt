package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.common.setupTapToExpand
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_cause_of_death.*

class CauseOfDeathFragment : Fragment() {

    companion object {
        fun newInstance() = CauseOfDeathFragment()
    }

    private lateinit var mParentViewModel: CreateFormViewModel
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

        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        mViewModel = ViewModelProviders.of(this, factory).get(CauseOfDeathViewModel::class.java)

        var isInit = true
        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            if (isInit) {
                causeOfDeathRecyclerView.doOnNextLayout {
                    (causeOfDeathRecyclerView.getChildAt(0) as CollapsibleContainer).expand(false)
                    isInit = false
                }
            }
            mAdapter.setRows(it)
        })
    }

}
