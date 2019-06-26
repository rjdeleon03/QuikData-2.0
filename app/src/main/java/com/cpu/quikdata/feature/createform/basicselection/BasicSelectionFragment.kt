package com.cpu.quikdata.feature.createform.basicselection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.observeOnly
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.dialog.InfoDialogFragment
import com.cpu.quikdata.dialog.ProgressDialogFragment
import com.cpu.quikdata.feature.main.MainActivity
import kotlinx.android.synthetic.main.fragment_basic_selection.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BasicSelectionFragment : BaseCreateFormFragment() {

    private lateinit var mNavController : NavController
    private var mDialog: DialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()

        setupClipping(basicSelectionRootLayout)
        selectionSendSaveButton.clickWithGuard {
            showProgressDialog()
            mParentViewModel.saveFormAsActual(true)
        }
        selectionFormDetailsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_formDetailsAndBaselineFragment) }
        selectionGenInfoButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionCaseStoriesButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel.form.observeOnly(viewLifecycleOwner)
        mParentViewModel.saveResult.observe(this, Observer {
            if (it == null) return@Observer
            mDialog?.dismiss()
            if (it) {
                Toast.makeText(context!!, R.string.form_item_submission_success, Toast.LENGTH_SHORT).show()
                activity!!.finish()
            } else {
                Toast.makeText(context!!, R.string.form_item_submission_failed, Toast.LENGTH_SHORT).show()

                // TODO: Disable navigation and other controls
            }
        })
    }

    private fun showProgressDialog() {
        mDialog?.dismiss()
        mDialog = ProgressDialogFragment.newInstance(textId = R.layout.dialog_progress)
        mDialog?.dialog?.setOnDismissListener { mParentViewModel.cancelSubmission() }
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
    }
}
