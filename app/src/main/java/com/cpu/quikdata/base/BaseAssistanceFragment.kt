package com.cpu.quikdata.base

import android.widget.Toast
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.ConfirmationDialogFragment

abstract class BaseAssistanceFragment: BaseCreateFormFragment() {

    protected var mIsItemLimitReached = false
    protected val mItemLimit = 5

    protected fun showConfirmationDialog(positiveButtonListener: () -> Unit,
                                         titleId: Int = R.string.assistance_delete_confirmation,
                                         layoutId: Int = R.layout.dialog_assistance_delete) {
        val dialog = ConfirmationDialogFragment.newInstance(titleId, layoutId)
        dialog.onPositiveButtonListener = {
            positiveButtonListener.invoke()
            Toast.makeText(context!!, R.string.text_delete_confirmation, Toast.LENGTH_SHORT).show()
        }
        dialog.show(childFragmentManager, ConfirmationDialogFragment.TAG)
    }
}