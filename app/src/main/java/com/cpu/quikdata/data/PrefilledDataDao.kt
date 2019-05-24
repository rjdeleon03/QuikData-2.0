package com.cpu.quikdata.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.PREFILLED_DATA_ID

@Dao
interface PrefilledDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(prefilledData: PrefilledData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(prefilledData: PrefilledData)

    @Query("SELECT * FROM prefilled_data WHERE id = :id")
    fun get(id: String = PREFILLED_DATA_ID): LiveData<PrefilledData>
}