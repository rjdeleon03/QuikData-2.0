package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_cause_of_death.*
import javax.inject.Inject

class CauseOfDeathFragment : BaseCollapsibleCreateFormFragment<CauseOfDeathAdapter, CauseOfDeathAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = CauseOfDeathFragment()
    }

    @Inject lateinit var mCauseOfDeathAdapterFactory: CauseOfDeathAdapter.Factory

    private val mViewModel: CauseOfDeathViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(CauseOfDeathViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.generalInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cause_of_death, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): CauseOfDeathAdapter {
        val adapter = mCauseOfDeathAdapterFactory.create({
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
