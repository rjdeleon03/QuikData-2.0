package com.cpu.quikdata.feature.createform.casestories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.casestories.CaseStoriesComplete
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import javax.inject.Inject

class CaseStoriesViewModel @Inject constructor (private val mRepository: CaseStoriesRepository)
    : ViewModel() {

    val caseStories: LiveData<CaseStoriesComplete>
        get() = mRepository.caseStories

    fun updateCaseStoriesText(data: CaseStories) =
        runOnIoThread { mRepository.updateCaseStoriesText(data) }

    fun insertImage(uri: String, id: String) =
        runOnIoThread { mRepository.insertImage(uri, id) }

    fun deleteImage(data: CaseStoriesImageItem) =
        runOnIoThread { mRepository.deleteImage(data) }
}
