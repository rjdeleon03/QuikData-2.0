package com.cpu.quikdata.base

import android.app.Application

abstract class BaseCreatableDataRepository<D>(application: Application) : BaseRepository<D>(application) {

    abstract fun createData(id: String = "")

    abstract fun deleteData(data: D)
}