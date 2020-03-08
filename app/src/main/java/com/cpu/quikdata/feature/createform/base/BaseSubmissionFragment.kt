package com.cpu.quikdata.feature.createform.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.observeOnly
import com.cpu.quikdata.common.observeProgress
import com.cpu.quikdata.common.showToast
import com.cpu.quikdata.dialog.InfoDialogFragment
import com.cpu.quikdata.dialog.ProgressDialogFragment
import com.cpu.quikdata.feature.createform.selection.worker.SubmissionWorker

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseSubmissionFragment : BaseCreateFormFragment() {

    protected var mDialog: ProgressDialogFragment? = null
    protected var mInfoDialog: InfoDialogFragment? = null

    protected fun showProgressDialog() {
        mDialog?.dismiss()
        mDialog = ProgressDialogFragment.newInstance(textId = R.layout.dialog_progress)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
        mDialog?.setOnDialogCanceledListener {
            //            mParentViewModel.cancelSubmission()
        }
    }

    protected fun hideProgressDialog() {
        mDialog?.dismiss()
        mDialog = null
    }

    protected fun showInfoDialog() {
        mInfoDialog = InfoDialogFragment.newInstance(R.string.form_item_submitting,
            R.layout.dialog_form_item_submitting)
        mInfoDialog?.show(childFragmentManager, InfoDialogFragment.TAG)
        mInfoDialog?.setOnDialogClosedListener {
            requireActivity().finish()
        }
    }

    protected val mSubmissionViewModel: SubmissionViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(SubmissionViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.submissionComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog =
            childFragmentManager.findFragmentByTag(ProgressDialogFragment.TAG) as? ProgressDialogFragment
        if (dialog != null) mDialog = dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel.form.observeOnly(viewLifecycleOwner)
        mParentViewModel.saveResult.observeProgress(viewLifecycleOwner, {
            mDialog?.dismiss()
            showToast(R.string.form_item_submission_success)
            requireActivity().finish()
        }, {
            mDialog?.dismiss()
            showToast(R.string.form_item_submission_failed)
        }, {
            showToast(R.string.form_item_submission_cancelled)
        }, {
            mDialog?.updateBasedOnProgress(it)
        })
    }

    protected fun initSubmissionWorker(isBasicMode: Boolean): OneTimeWorkRequest {
        showInfoDialog()
        return OneTimeWorkRequest.Builder(SubmissionWorker::class.java)
            .setInputData(SubmissionWorker.setFormData(mParentViewModel.formId, isBasicMode))
            .build()
    }
}