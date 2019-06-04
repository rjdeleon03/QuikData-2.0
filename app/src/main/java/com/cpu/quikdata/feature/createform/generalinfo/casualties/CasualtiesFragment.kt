package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CasualtiesViewModel::class.java)
        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
