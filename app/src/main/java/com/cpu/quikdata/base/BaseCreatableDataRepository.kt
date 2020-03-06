package com.cpu.quikdata.base

abstract class BaseCreatableDataRepository<D>() {

    abstract fun createData(id: String = "")

    abstract fun updateData(data: D)

    abstract fun deleteData(data: D)
}