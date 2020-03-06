package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.showConfirmationDialog
import kotlinx.android.synthetic.main.fragment_wash_assistance.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*
import javax.inject.Inject

class WashAssistanceFragment : BaseAssistanceFragment<WashAssistanceAdapter, WashAssistanceAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = WashAssistanceFragment()
    }

    @Inject lateinit var mWashAssistanceAdapterFactory: WashAssistanceAdapter.Factory

    private val mViewModel: WashAssistanceViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(WashAssistanceViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.waterSanitationInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wash_assistance, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): WashAssistanceAdapter {
        val adapter = mWashAssistanceAdapterFactory.create({ mViewModel.updateRow(it) }, {
            showConfirmationDialog ({ mViewModel.deleteRow(it) })
        }, expandedItemIndex)
        washAssistanceRecyclerView.recyclerView.adapter = adapter
        return adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        washAssistanceAddButton.clickWithGuard {
            if (mIsItemLimitReached) {
                Toast.makeText(requireContext(), R.string.assistance_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(requireContext(), R.string.assistance_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.washAssistance.observe(viewLifecycleOwner, Observer {
            washAssistanceRecyclerView.updateDisplayBasedOnItemCount(it.size)
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
