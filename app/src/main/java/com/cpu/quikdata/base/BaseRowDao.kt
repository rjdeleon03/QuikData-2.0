package com.cpu.quikdata.base

import androidx.room.*

@Dao
interface BaseRowDao<R> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(rows: List<R>)

    @Delete
    suspend fun delete(row: R)
}