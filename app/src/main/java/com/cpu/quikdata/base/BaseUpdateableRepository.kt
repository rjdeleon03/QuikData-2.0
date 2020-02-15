package com.cpu.quikdata.base

abstract class BaseUpdateableRepository<D> {

    abstract suspend fun updateData(data: D)
}