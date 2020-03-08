package com.cpu.quikdata.feature.createform.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import javax.inject.Inject

class SubmissionViewModel @Inject constructor(private val mRepository: SubmissionRepository) :
    ViewModel() {

    val progress: LiveData<WorkInfo>
        get() = mRepository.progress

    fun submitFormData(workManager: WorkManager, workRequest: OneTimeWorkRequest) =
        mRepository.submitFormData(workManager, workRequest)

}