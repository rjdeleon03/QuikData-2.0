package com.cpu.quikdata.base

import androidx.room.*

@Dao
interface BaseDao<R> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(row: R)

    @Delete
    suspend fun delete(row: R)
}