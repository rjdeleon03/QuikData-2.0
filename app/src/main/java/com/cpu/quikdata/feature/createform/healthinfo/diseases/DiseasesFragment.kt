package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_diseases.*
import javax.inject.Inject

class DiseasesFragment : BaseCollapsibleCreateFormFragment<DiseasesAdapter, DiseasesAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun start() = DiseasesFragment()
    }

    @Inject
    lateinit var mViewModel: DiseasesViewModel

    @Inject
    lateinit var mAdapterFactory: DiseasesAdapter.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diseases, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): DiseasesAdapter {
        val adapter = mAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        diseasesRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.diseases.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
