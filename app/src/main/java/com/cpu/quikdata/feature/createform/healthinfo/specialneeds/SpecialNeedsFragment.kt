package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import kotlinx.android.synthetic.main.fragment_special_needs.*

class SpecialNeedsFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = SpecialNeedsFragment()
    }

    private lateinit var mViewModel: SpecialNeedsViewModel
    private lateinit var mAdapter: SpecialNeedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_special_needs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = SpecialNeedsAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        specialNeedsRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(SpecialNeedsViewModel::class.java)
        mViewModel.specialNeeds.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
