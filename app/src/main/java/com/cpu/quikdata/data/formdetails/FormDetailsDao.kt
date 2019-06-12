package com.cpu.quikdata.data.formdetails

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FormDetailsDao : BaseDao<FormDetails> {

    @Query("SELECT * FROM form_details WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FormDetails>

    @Query("SELECT * FROM form_details WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): FormDetails
}