package com.cpu.quikdata.feature.createform.healthinfo.healthassistance

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
import kotlinx.android.synthetic.main.fragment_health_assistance.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*
import javax.inject.Inject

class HealthAssistanceFragment : BaseAssistanceFragment<HealthAssistanceAdapter, HealthAssistanceAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = HealthAssistanceFragment()
    }

    @Inject lateinit var mHealthAssistanceAdapterFactory: HealthAssistanceAdapter.Factory

    private val mViewModel: HealthAssistanceViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(HealthAssistanceViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.healthInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_assistance, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): HealthAssistanceAdapter {
        val adapter = mHealthAssistanceAdapterFactory.create({ mViewModel.updateRow(it) }, {
            showConfirmationDialog ({ mViewModel.deleteRow(it) })
        }, expandedItemIndex)
        healthAssistanceRecyclerView.recyclerView.adapter = adapter
        return adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        healthAssistanceAddButton.clickWithGuard {
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

        mViewModel.healthAssistance.observe(viewLifecycleOwner, Observer {
            healthAssistanceRecyclerView.updateDisplayBasedOnItemCount(it.size)
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
