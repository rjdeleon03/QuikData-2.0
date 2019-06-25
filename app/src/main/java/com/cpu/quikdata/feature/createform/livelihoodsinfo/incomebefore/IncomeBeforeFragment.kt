package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

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
import com.cpu.quikdata.common.showConfirmationDialog
import kotlinx.android.synthetic.main.fragment_income_before.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*

class IncomeBeforeFragment : BaseAssistanceFragment<IncomeBeforeAdapter, IncomeBeforeAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = IncomeBeforeFragment()
    }

    private lateinit var mViewModel: IncomeBeforeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_before, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): IncomeBeforeAdapter {
        val adapter = IncomeBeforeAdapter(context!!, { mViewModel.updateRow(it) }, {
            showConfirmationDialog({ mViewModel.deleteRow(it) },
                R.string.income_source_delete_confirmation,
                R.layout.dialog_income_source_delete)
        }, expandedItemIndex)
        incomeBeforeRecyclerView.recyclerView.adapter = adapter
        return adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeBeforeAddButton.clickWithGuard {

            if (mIsItemLimitReached) {
                Toast.makeText(context!!, R.string.income_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(context!!, R.string.income_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(IncomeBeforeViewModel::class.java)
        mViewModel.incomeBefore.observe(viewLifecycleOwner, Observer {
            incomeBeforeRecyclerView.updateDisplayBasedOnItemCount(it.size)
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
