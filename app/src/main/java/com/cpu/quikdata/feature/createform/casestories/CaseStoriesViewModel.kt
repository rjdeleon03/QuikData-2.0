package com.cpu.quikdata.feature.createform.casestories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.casestories.CaseStoriesComplete
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem

class CaseStoriesViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = CaseStoriesRepository(application, formId)

    val caseStories: LiveData<CaseStoriesComplete>
        get() = mRepository.caseStories

    fun updateCaseStoriesText(data: CaseStories) = mRepository.updateCaseStoriesText(data)

    fun insertImage(uri: String) = mRepository.insertImage(uri)

    fun deleteImage(data: CaseStoriesImageItem) = mRepository.deleteImage(data)
}
