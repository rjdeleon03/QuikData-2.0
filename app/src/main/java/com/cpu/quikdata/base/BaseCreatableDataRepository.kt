package com.cpu.quikdata.base

abstract class BaseCreatableDataRepository<D> : BaseUpdateableRepository<D>() {

    abstract suspend fun createData(id: String = "")

    abstract suspend fun deleteData(data: D)
}