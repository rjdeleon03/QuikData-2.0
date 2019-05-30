package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.common.setupTapToExpand
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_casualties.*

class CasualtiesFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = CasualtiesFragment()
    }

    private lateinit var mViewModel: CasualtiesViewModel
    private lateinit var mAdapter: CasualtiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_casualties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CasualtiesAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        casualtiesRecyclerView.adapter = mAdapter
        casualtiesRecyclerView.setupTapToExpand(context!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CasualtiesViewModel::class.java)
        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
