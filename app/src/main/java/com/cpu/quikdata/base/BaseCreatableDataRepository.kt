package com.cpu.quikdata.base

abstract class BaseCreatableDataRepository<D>() {

    abstract suspend fun createData(id: String = "")

    abstract suspend fun updateData(data: D)

    abstract suspend fun deleteData(data: D)
}