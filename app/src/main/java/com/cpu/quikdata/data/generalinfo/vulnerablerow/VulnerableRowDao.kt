package com.cpu.quikdata.data.generalinfo.vulnerablerow

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VulnerableRowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vulnerableRow: VulnerableRow)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vulnerableRow: VulnerableRow)

    @Delete
    fun delete(vulnerableRow: VulnerableRow)

    @Query("SELECT * FROM vulnerable_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<VulnerableRow>>
}