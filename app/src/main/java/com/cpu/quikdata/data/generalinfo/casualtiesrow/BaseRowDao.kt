package com.cpu.quikdata.data.generalinfo.casualtiesrow

import androidx.room.*

@Dao
interface BaseRowDao<R> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(row: R)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(row: R)

    @Delete
    fun delete(row: R)
}