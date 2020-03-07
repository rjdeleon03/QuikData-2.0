package com.cpu.quikdata.feature.createform.selection.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.*

class SubmissionWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context.applicationContext, workerParams) {

    companion object {
        @JvmStatic
        fun cancelSubmissionWork(workId: String, context: Context) {
            val id = UUID.fromString(workId)
            WorkManager.getInstance(context).cancelWorkById(id)
        }
    }

    override suspend fun doWork(): Result {

        return Result.success()
    }
}