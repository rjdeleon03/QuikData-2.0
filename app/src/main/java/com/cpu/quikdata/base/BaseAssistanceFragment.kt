package com.cpu.quikdata.base

import android.widget.Toast
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.ConfirmationDialogFragment

abstract class BaseAssistanceFragment<A, H>: BaseCollapsibleCreateFormFragment<A, H>()
        where A : BaseCollapsibleAdapter<*, H>, H : BaseCollapsibleAdapter.ViewHolder<*> {

    protected var mIsItemLimitReached = false
    protected val mItemLimit = 5
}