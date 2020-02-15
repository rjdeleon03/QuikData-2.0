package com.cpu.quikdata.feature.createform.casestories

import android.net.Uri
import androidx.lifecycle.LiveData
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.casestories.CaseStoriesComplete
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import com.cpu.quikdata.utils.getDateTimeNowInLong

class CaseStoriesRepository(private val mDatabase: AppDatabase, val formId: String) {

    private val mCaseStories = mDatabase.caseStoriesDao().getByFormId(formId)

    val caseStories: LiveData<CaseStoriesComplete>
        get() = mCaseStories

    suspend fun updateCaseStoriesText(data: CaseStories) {
        mCaseStories.value?.root?.let {
            it.copyFrom(data)
            mDatabase.caseStoriesDao().update(it)
        }
    }

    suspend fun insertImage(uri: String, id: String) {
        mCaseStories.value?.root?.let {
            val caseStoriesId = it.id
            mDatabase.caseStoriesImageItemDao().insert(CaseStoriesImageItem(
                id = id,
                dateCreated = getDateTimeNowInLong(),
                uri = uri,
                caseStoriesId = caseStoriesId
            ))
        }
    }

    suspend fun deleteImage(data: CaseStoriesImageItem) {
        Uri.parse(data.uri).deleteFile()
        mDatabase.caseStoriesImageItemDao().delete(data)
    }
}