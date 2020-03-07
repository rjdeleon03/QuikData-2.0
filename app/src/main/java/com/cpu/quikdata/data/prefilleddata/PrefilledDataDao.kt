package com.cpu.quikdata.data.prefilleddata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.PREFILLED_DATA_ID

@Dao
interface PrefilledDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prefilledData: PrefilledData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(prefilledData: PrefilledData)

    @Query("SELECT * FROM prefilled_data WHERE id = :id")
    fun get(id: String = PREFILLED_DATA_ID): LiveData<PrefilledData>

    @Query("SELECT * FROM prefilled_data WHERE id = :id")
    fun getNonLive(id: String = PREFILLED_DATA_ID): PrefilledData
}