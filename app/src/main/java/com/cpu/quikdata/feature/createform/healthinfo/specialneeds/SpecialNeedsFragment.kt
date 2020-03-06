package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_special_needs.*
import javax.inject.Inject

class SpecialNeedsFragment : BaseCollapsibleCreateFormFragment<SpecialNeedsAdapter, SpecialNeedsAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = SpecialNeedsFragment()
    }

    @Inject lateinit var mSpecialNeedsAdapterFactory: SpecialNeedsAdapter.Factory

    private val mViewModel: SpecialNeedsViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(SpecialNeedsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.healthInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_special_needs, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): SpecialNeedsAdapter {
        val adapter = mSpecialNeedsAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        specialNeedsRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.specialNeeds.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
