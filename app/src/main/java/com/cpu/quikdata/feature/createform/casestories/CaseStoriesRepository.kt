package com.cpu.quikdata.feature.createform.casestories

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.casestories.CaseStoriesComplete
import com.cpu.quikdata.utils.runOnIoThread

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
}