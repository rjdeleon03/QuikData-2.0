package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

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
import kotlinx.android.synthetic.main.fragment_income_after.*

class IncomeAfterFragment : BaseAssistanceFragment() {

    companion object {
        fun newInstance() = IncomeAfterFragment()
    }

    private lateinit var mViewModel: IncomeAfterViewModel
    private lateinit var mAdapter: IncomeAfterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_after, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = IncomeAfterAdapter(context!!, { mViewModel.updateRow(it) }) {
            showConfirmationDialog({ mViewModel.deleteRow(it) },
                R.string.income_source_delete_confirmation,
                R.layout.dialog_income_source_delete)
        }
        incomeAfterRecyclerView.adapter = mAdapter
        incomeAfterAddButton.clickWithGuard {

            if (mIsItemLimitReached) {
                // TODO: Update this with a dialog
                Toast.makeText(context!!, R.string.income_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(context!!, R.string.income_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(IncomeAfterViewModel::class.java)
        mViewModel.incomeAfter.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
