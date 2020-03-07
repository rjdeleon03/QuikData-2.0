package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.base.BaseCollapsibleEvacuationItemFragment
import kotlinx.android.synthetic.main.fragment_evacuation_age.*
import javax.inject.Inject

class EvacuationAgeFragment : BaseCollapsibleEvacuationItemFragment<EvacuationAgeAdapter, EvacuationAgeAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance(): EvacuationAgeFragment {
            return EvacuationAgeFragment()
        }
    }

    @Inject lateinit var mEvacuationAgeAdapterFactory: EvacuationAgeAdapter.Factory

    private val mViewModel: EvacuationAgeViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(EvacuationAgeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mEvacuationItemComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_age, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): EvacuationAgeAdapter {
        val adapter = mEvacuationAgeAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        evacuationAgeRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.evacuationAge.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
