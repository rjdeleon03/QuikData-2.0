package com.cpu.quikdata.base

abstract class BaseCreatableDataRepository<D> : BaseUpdateableRepository<D>() {

    abstract fun createData(id: String = "")

    abstract fun deleteData(data: D)
}