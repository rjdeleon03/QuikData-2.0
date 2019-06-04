package com.cpu.quikdata.feature.createform.evacuationinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.fragment_evacuation_info.*

class EvacuationInfoFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = EvacuationInfoFragment()
    }

    private lateinit var mViewModel: EvacuationInfoViewModel
    private lateinit var mAdapter: EvacuationInfoAdapter
    private val mItemLimit = 10
    private var mIsItemLimitReached = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = EvacuationInfoAdapter(context!!)
        evacuationRecyclerView.adapter = mAdapter

        evacuationAddButton.clickWithGuard {
            if (mIsItemLimitReached) {
                // TODO: Update this with a dialog
                Toast.makeText(context!!, R.string.evacuation_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createEvacuationInfo()
                Toast.makeText(context!!, R.string.evacuation_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(EvacuationInfoViewModel::class.java)
        mViewModel.evacuationInfos.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
            if (it.size >= mItemLimit) {
                mIsItemLimitReached = true
            }
        })
    }

}
