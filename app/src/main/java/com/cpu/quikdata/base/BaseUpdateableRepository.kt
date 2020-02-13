package com.cpu.quikdata.base

abstract class BaseUpdateableRepository<D>() {

    abstract fun updateData(data: D)
}