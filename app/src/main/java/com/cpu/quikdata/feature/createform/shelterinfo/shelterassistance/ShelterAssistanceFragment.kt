package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceFragment
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.fragment_shelter_assistance.*

class ShelterAssistanceFragment : BaseAssistanceFragment() {

    companion object {
        fun newInstance() = ShelterAssistanceFragment()
    }

    private lateinit var mViewModel: ShelterAssistanceViewModel
    private lateinit var mAdapter: ShelterAssistanceAdapter

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
        shelterAssistanceAddButton.clickWithGuard {

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

        mViewModel = ViewModelProviders.of(this, mFactory).get(ShelterAssistanceViewModel::class.java)
        mViewModel.shelterAssistance.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
            isItemLimitReached = it.size >= itemLimit
        })
    }

}
