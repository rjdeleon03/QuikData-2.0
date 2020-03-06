package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_house_damage.*
import javax.inject.Inject

class HouseDamageFragment : BaseCollapsibleCreateFormFragment<HouseDamageAdapter, HouseDamageAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = HouseDamageFragment()
    }

    @Inject lateinit var mHouseDamageAdapterFactory: HouseDamageAdapter.Factory

    private val mViewModel: HouseDamageViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(HouseDamageViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.shelterInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_damage, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): HouseDamageAdapter {
        val adapter = mHouseDamageAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        houseDamageRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.houseDamage.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
