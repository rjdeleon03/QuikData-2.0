package com.cpu.quikdata.feature.createform.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import javax.inject.Inject

class SubmissionRepository @Inject constructor() {

    private val mProgress = MediatorLiveData<WorkInfo>()

    val progress: LiveData<WorkInfo>
        get() = mProgress

    fun submitFormData(workManager: WorkManager, workRequest: OneTimeWorkRequest) {
        val id = workRequest.id
        workManager.enqueue(workRequest)

        val workLiveData = workManager.getWorkInfoByIdLiveData(id)
        mProgress.addSource(workLiveData) {
            mProgress.value = it
            if (it.state.isFinished) {
                mProgress.removeSource(workLiveData)
            }
        }
    }
}