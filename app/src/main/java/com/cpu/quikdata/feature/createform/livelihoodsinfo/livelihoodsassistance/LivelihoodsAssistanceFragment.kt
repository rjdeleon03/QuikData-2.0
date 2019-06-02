package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceFragment
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_livelihoods_assistance.*

class LivelihoodsAssistanceFragment : BaseAssistanceFragment() {

    companion object {
        fun newInstance() = LivelihoodsAssistanceFragment()
    }

    private lateinit var mViewModel: LivelihoodsAssistanceViewModel
    private lateinit var mAdapter: LivelihoodsAssistanceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_assistance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = LivelihoodsAssistanceAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        livelihoodsAssistanceRecyclerView.adapter = mAdapter
        livelihoodsAssistanceRecyclerView.setupTapToExpand(context!!)
        livelihoodsAssistanceAddButton.clickWithGuard {

            if (isItemLimitReached) {
                // TODO: Update this with a dialog
                Toast.makeText(context!!, R.string.assistance_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(context!!, R.string.assistance_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(LivelihoodsAssistanceViewModel::class.java)
        mViewModel.livelihoodsAssistance.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
            isItemLimitReached = it.size >= itemLimit
        })
    }

}
