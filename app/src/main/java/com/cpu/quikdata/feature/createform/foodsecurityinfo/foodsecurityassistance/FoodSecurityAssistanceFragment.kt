package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

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
import kotlinx.android.synthetic.main.fragment_food_security_assistance.*
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*
import javax.inject.Inject

class FoodSecurityAssistanceFragment :
    BaseAssistanceFragment<FoodSecurityAssistanceAdapter, FoodSecurityAssistanceAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = FoodSecurityAssistanceFragment()
    }

    @Inject lateinit var mFoodSecurityAssistanceAdapterFactory: FoodSecurityAssistanceAdapter.Factory

    private val mViewModel: FoodSecurityAssistanceViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(FoodSecurityAssistanceViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.foodSecurityInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_security_assistance, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): FoodSecurityAssistanceAdapter {
        val adapter = mFoodSecurityAssistanceAdapterFactory.create({ mViewModel.updateRow(it) }, {
            showConfirmationDialog ({ mViewModel.deleteRow(it) })
        }, expandedItemIndex)
        foodSecurityAssistanceRecyclerView.recyclerView.adapter = adapter
        return adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodSecurityAssistanceAddButton.clickWithGuard {
            if (mIsItemLimitReached) {
                // TODO: Update this with a dialog
                Toast.makeText(requireContext(), R.string.assistance_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.createRow()
                Toast.makeText(requireContext(), R.string.assistance_added, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.foodSecurityAssistance.observe(viewLifecycleOwner, Observer {
            foodSecurityAssistanceRecyclerView.updateDisplayBasedOnItemCount(it.size)
            mAdapter.setRows(it)
            mIsItemLimitReached = it.size >= mItemLimit
        })
    }

}
