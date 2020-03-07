package com.cpu.quikdata.feature.createform.selection.worker

import android.content.Context
import androidx.work.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.app.module.ChildWorkerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class SubmissionWorker(context: Context, workerParams: WorkerParameters, private val mDatabase: AppDatabase) :
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

    class Factory @Inject constructor(private val mDatabase: Provider<AppDatabase>): ChildWorkerFactory {
        override fun create(appContext: Context, workerParams: WorkerParameters): ListenableWorker {
            return SubmissionWorker(appContext, workerParams, mDatabase.get())
        }

    }
}