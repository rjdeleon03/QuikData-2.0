package com.cpu.quikdata.base

import android.app.Application
import com.cpu.quikdata.data.AppDatabase

abstract class BaseCreatableDataRepository<D>(application: Application) : BaseRepository<D>(application) {

    abstract fun createData()

    abstract fun deleteData(data: D)
}