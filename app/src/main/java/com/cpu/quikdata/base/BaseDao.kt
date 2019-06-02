package com.cpu.quikdata.base

import androidx.room.*

@Dao
interface BaseDao<R> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(row: R)

    @Delete
    fun delete(row: R)
}