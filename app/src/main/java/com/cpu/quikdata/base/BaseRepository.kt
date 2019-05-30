package com.cpu.quikdata.base

import android.app.Application
import com.cpu.quikdata.data.AppDatabase

abstract class BaseRepository<D>(application: Application) {

    protected val mDatabase = AppDatabase.get(application)

    abstract fun updateData(data: D)
}