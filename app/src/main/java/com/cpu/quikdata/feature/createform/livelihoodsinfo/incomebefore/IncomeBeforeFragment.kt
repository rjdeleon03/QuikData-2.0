package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

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
import kotlinx.android.synthetic.main.fragment_income_before.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*
import javax.inject.Inject

class IncomeBeforeFragment : BaseAssistanceFragment<IncomeBeforeAdapter, IncomeBeforeAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = IncomeBeforeFragment()
    }

    @Inject lateinit var mIncomeBeforeAdapterFactory: IncomeBeforeAdapter.Factory

    private val mViewModel: IncomeBeforeViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(IncomeBeforeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.livelihoodsInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_before, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): IncomeBeforeAdapter {
        val adapter = mIncomeBeforeAdapterFactory.create({ mViewModel.updateRow(it) }, {
            showConfirmationDialog({ mViewModel.deleteRow(it) },
                R.string.income_source_delete_confirmation,
                R.layout.dialog_income_source_delete,
                R.string.income_source_delete_finished)
        }, expandedItemIndex)
        incomeBeforeRecyclerView.recyclerView.adapter = adapter
        return adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeBeforeAddButton.clickWithGuard {

            if (mIsItemLimitReached) {
                Toast.makeText(requireContext(), R.string.income_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(requireContext(), R.string.income_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.incomeBefore.observe(viewLifecycleOwner, Observer {
            incomeBeforeRecyclerView.updateDisplayBasedOnItemCount(it.size)
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
