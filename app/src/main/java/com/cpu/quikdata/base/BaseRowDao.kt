package com.cpu.quikdata.base

import androidx.room.*

@Dao
interface BaseRowDao<R> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(rows: List<R>)

    @Delete
    fun delete(row: R)
}