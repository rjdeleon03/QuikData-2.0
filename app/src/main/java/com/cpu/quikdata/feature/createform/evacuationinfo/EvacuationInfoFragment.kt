package com.cpu.quikdata.feature.createform.evacuationinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.showConfirmationDialog
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.fragment_evacuation_info.*

class EvacuationInfoFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = EvacuationInfoFragment()
    }

    private lateinit var mViewModel: EvacuationInfoViewModel
    private lateinit var mAdapter: EvacuationInfoAdapter
    private lateinit var mNavController: NavController
    private val mItemLimit = 5
    private var mIsItemLimitReached = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()
        mAdapter = EvacuationInfoAdapter(context!!, {
            val action = EvacuationInfoFragmentDirections
                .actionEvacuationInfoFragmentToEvacuationContainerFragment(it, true)
            mNavController.navigate(action)
        }, {
            showConfirmationDialog({
                mViewModel.deleteEvacuationInfo(it)},
                R.string.evacuation_delete_confirmation,
                R.layout.dialog_evacuation_delete,
                R.string.evacuation_delete_finished)
        })
        evacuationRecyclerView.adapter = mAdapter

        evacuationAddButton.clickWithGuard {
            if (mIsItemLimitReached) {
                // TODO: Update this with a dialog
                Toast.makeText(context!!, R.string.evacuation_add_limit_error, Toast.LENGTH_SHORT).show()
            } else {
                val id = generateId()
                mViewModel.createEvacuationInfo(id)

                Toast.makeText(context!!, R.string.evacuation_added, Toast.LENGTH_SHORT).show()
                val action = EvacuationInfoFragmentDirections
                    .actionEvacuationInfoFragmentToEvacuationContainerFragment(id, false)
                mNavController.navigate(action)
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

    override fun onResume() {
        super.onResume()
        (activity as CreateFormActivity).setSubtitle()
    }

}
