package com.cpu.quikdata.feature.createform

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.observeOnly
import com.cpu.quikdata.common.showToast
import com.cpu.quikdata.dialog.ProgressDialogFragment

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseSelectionFragment : BaseCreateFormFragment() {

    protected var mDialog: ProgressDialogFragment? = null

    protected fun showProgressDialog() {
        mDialog?.dismiss()
        mDialog = ProgressDialogFragment.newInstance(textId = R.layout.dialog_progress)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
        mDialog?.setOnDialogCanceledListener {
            mParentViewModel.cancelSubmission()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel.form.observeOnly(viewLifecycleOwner)
        mParentViewModel.saveResult.observe(this, Observer {
            when (it) {
                ProgressNotification.FINISHED -> {
                    mDialog?.dismiss()
                    showToast(R.string.form_item_submission_success)
                    activity!!.finish()
                }
                ProgressNotification.ERROR_OCCURRED -> {
                    mDialog?.dismiss()
                    showToast(R.string.form_item_submission_failed)
                }
                ProgressNotification.CANCELLED -> {
                    showToast(R.string.form_item_submission_cancelled)
                }
                else -> {
                    mDialog?.updateBasedOnProgress(it)
                }
            }
        })
    }
}