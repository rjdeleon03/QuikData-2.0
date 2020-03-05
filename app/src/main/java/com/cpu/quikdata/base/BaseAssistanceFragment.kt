package com.cpu.quikdata.base

abstract class BaseAssistanceFragment<A, H>: BaseCollapsibleCreateFormFragment<A, H>()
        where A : BaseCollapsibleAdapter<*, H>, H : BaseCollapsibleAdapter.ViewHolder<*> {

    protected var mIsItemLimitReached = false
    protected val mItemLimit = 5
}