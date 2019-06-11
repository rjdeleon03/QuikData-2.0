package com.cpu.quikdata.data.casestories

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface CaseStoriesDao : BaseDao<CaseStories> {

    @Query("SELECT * FROM case_stories WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<CaseStories>

    @Query("SELECT * FROM case_stories WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): CaseStories
}