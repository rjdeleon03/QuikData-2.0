package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupTapToExpand
import kotlinx.android.synthetic.main.fragment_shelter_assistance.*

class ShelterAssistanceFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = ShelterAssistanceFragment()
    }

    private lateinit var mViewModel: ShelterAssistanceViewModel
    private lateinit var mAdapter: ShelterAssistanceAdapter
    private var isItemLimitReached = false
    private val itemLimit = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_assistance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ShelterAssistanceAdapter(context!!) {
            mViewModel.updateRow(it)
        }
        shelterAssistanceRecyclerView.adapter = mAdapter
        shelterAssistanceRecyclerView.setupTapToExpand(context!!)
        shelterAssistanceAddButton.clickWithGuard {

            // TODO: Update this with a dialog
            if (isItemLimitReached) {
                Toast.makeText(context!!, R.string.assistance_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(ShelterAssistanceViewModel::class.java)
        mViewModel.shelterAssistance.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
            isItemLimitReached = it.size > itemLimit
        })
    }

}
