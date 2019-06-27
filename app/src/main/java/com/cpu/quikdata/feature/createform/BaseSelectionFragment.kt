package com.cpu.quikdata.feature.createform

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.observeOnly
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
            Toast.makeText(context!!, R.string.form_item_submission_cancelled, Toast.LENGTH_SHORT).show()
        }
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
}