package com.cpu.quikdata.feature.createform.casestories

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.casestories.CaseStoriesComplete
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDateTime

class CaseStoriesRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mCaseStories = mDatabase.caseStoriesDao().getByFormId(formId)

    val caseStories: LiveData<CaseStoriesComplete>
        get() = mCaseStories

    fun updateCaseStoriesText(data: CaseStories) {
        runOnIoThread {
            val oldCaseStoriesText = mCaseStories.value!!.root!!
            oldCaseStoriesText.copyFrom(data)
            mDatabase.caseStoriesDao().update(oldCaseStoriesText)
        }
    }

    fun insertImage(uri: String, id: String) {
        runOnIoThread {
            val caseStoriesId = mCaseStories.value!!.root!!.id
            mDatabase.caseStoriesImageItemDao().insert(CaseStoriesImageItem(
                id = id,
                dateCreated = getDateTimeNowInLong(),
                uri = uri,
                caseStoriesId = caseStoriesId
            ))
        }
    }

    fun deleteImage(data: CaseStoriesImageItem) {
        runOnIoThread {
            Uri.parse(data.uri).deleteFile()
            mDatabase.caseStoriesImageItemDao().delete(data)
        }
    }
}