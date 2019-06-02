package com.cpu.quikdata.base

abstract class BaseAssistanceFragment: BaseCreateFormFragment() {

    protected var isItemLimitReached = false
    protected val itemLimit = 10
}