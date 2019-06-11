package com.cpu.quikdata.data.casestories.casestoriesimage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface CaseStoriesImageItemDao : BaseRowDao<CaseStoriesImageItem> {

    @Query("SELECT * FROM case_stories_image_item WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<CaseStoriesImageItem>>

    @Query("SELECT * FROM case_stories_image_item WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormIdNonLive(formId: String): List<CaseStoriesImageItem>
}