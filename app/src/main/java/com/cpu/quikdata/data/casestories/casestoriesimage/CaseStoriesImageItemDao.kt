package com.cpu.quikdata.data.casestories.casestoriesimage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface CaseStoriesImageItemDao : BaseRowDao<CaseStoriesImageItem> {

    @Query("SELECT * FROM case_stories_image_item WHERE caseStoriesId = :caseStoriesId ORDER BY dateCreated")
    fun getByCaseStoriesId(caseStoriesId: String): LiveData<List<CaseStoriesImageItem>>

    @Query("SELECT * FROM case_stories_image_item WHERE caseStoriesId = :caseStoriesId ORDER BY dateCreated")
    fun getByCaseStoriesIdNonLive(caseStoriesId: String): List<CaseStoriesImageItem>
}