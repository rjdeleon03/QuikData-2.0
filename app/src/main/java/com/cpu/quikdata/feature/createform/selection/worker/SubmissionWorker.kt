package com.cpu.quikdata.feature.createform.selection.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.*

class SubmissionWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    companion object {
        private const val FORM_ID_KEY = "FORM_ID_KEY"

        @JvmStatic
        fun cancelSubmissionWork(workId: String, context: Context) {
            val id = UUID.fromString(workId)
            WorkManager.getInstance(context).cancelWorkById(id)
        }

        @JvmStatic
        fun createFormData(id: String): Data {
            val data = Data.Builder()
            data.putString(FORM_ID_KEY, id)
            return data.build()
        }
    }

    override suspend fun doWork(): Result {
        return Result.success()
    }
}