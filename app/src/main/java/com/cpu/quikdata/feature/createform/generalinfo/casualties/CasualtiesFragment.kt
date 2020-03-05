package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import com.cpu.quikdata.base.BaseCreateFormFragment
import kotlinx.android.synthetic.main.fragment_casualties.*

class CasualtiesFragment : BaseCollapsibleCreateFormFragment<CasualtiesAdapter, CasualtiesAdapter.ViewHolder>() {

    companion object {
        fun newInstance() = CasualtiesFragment()
    }

    private lateinit var mViewModel: CasualtiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_casualties, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): CasualtiesAdapter {
        val adapter = CasualtiesAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        casualtiesRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProvider(this, mFactory).get(CasualtiesViewModel::class.java)
        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
