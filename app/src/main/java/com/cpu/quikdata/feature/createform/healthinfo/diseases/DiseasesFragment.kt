package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_diseases.*

class DiseasesFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = DiseasesFragment()
    }

    private lateinit var mViewModel: DiseasesViewModel
    private lateinit var mAdapter: DiseasesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diseases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = DiseasesAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        diseasesRecyclerView.adapter = mAdapter
        diseasesRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(DiseasesViewModel::class.java)
        mViewModel.diseases.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
