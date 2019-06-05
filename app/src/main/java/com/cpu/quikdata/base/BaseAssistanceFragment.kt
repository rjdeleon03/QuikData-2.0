package com.cpu.quikdata.base

import android.widget.Toast
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.ConfirmationDialogFragment

abstract class BaseAssistanceFragment: BaseCreateFormFragment() {

    protected var mIsItemLimitReached = false
    protected val mItemLimit = 5

    protected fun showConfirmationDialog(positiveButtonListener: () -> Unit,
                                         titleId: Int = R.string.assistance_delete_confirmation,
                                         layoutId: Int = R.layout.dialog_assistance_delete,
                                         toastId: Int = R.string.assistance_delete_finished) {
        val dialog = ConfirmationDialogFragment.newInstance(titleId, layoutId)
        dialog.onPositiveButtonListener = {
            positiveButtonListener.invoke()
            Toast.makeText(context!!, toastId, Toast.LENGTH_SHORT).show()
        }
        dialog.show(childFragmentManager, ConfirmationDialogFragment.TAG)
    }
}