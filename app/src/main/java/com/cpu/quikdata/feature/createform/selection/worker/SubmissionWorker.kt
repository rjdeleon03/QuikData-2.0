package com.cpu.quikdata.feature.createform.selection.worker

import android.content.Context
import androidx.work.*
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.di.app.module.ChildWorkerFactory
import com.cpu.quikdata.utils.getDateTimeNowInLong
import java.util.*
import javax.inject.Inject
import javax.inject.Provider


class SubmissionWorker(
    context: Context, workerParams: WorkerParameters,
    private val mFirebaseHelper: FirebaseHelper,
    private val mDatabase: AppDatabase
) :
    CoroutineWorker(context, workerParams) {

    companion object {
        const val FORM_ID_KEY = "FORM_ID_KEY"
        const val MODE_KEY = "MODE_KEY"

        @JvmStatic
        fun cancelSubmissionWork(context: Context, workId: String) {
            val id = UUID.fromString(workId)
            WorkManager.getInstance(context).cancelWorkById(id)
        }

        @JvmStatic
        fun setFormData(formId: String, isBasicMode: Boolean = false): Data {
            return Data.Builder()
                .putString(FORM_ID_KEY, formId)
                .putBoolean(MODE_KEY, isBasicMode)
                .build()
        }
    }

    override suspend fun doWork(): Result {
        inputData.getString(FORM_ID_KEY)?.let { formId ->
            val form = retrieveFormAndSaveAsNonTemporary(formId)

            val isBasicMode = inputData.getBoolean(MODE_KEY, true)
            if (isBasicMode) {
                mFirebaseHelper.submitBasicData(form.id)
            } else {
                mFirebaseHelper.submitAllData(form.id)
            }

            return Result.success()
        }
        return Result.failure()
    }

    private suspend fun retrieveFormAndSaveAsNonTemporary(formId: String): Form {
        return mDatabase.formDao().getByIdNonLive(formId).apply {
            isTemporary = false
            dateModified = getDateTimeNowInLong()
            mDatabase.formDao().update(this)
        }
    }

    class Factory @Inject constructor(
        private val mFirebaseHelper: Provider<FirebaseHelper>,
        private val mDatabase: Provider<AppDatabase>
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, workerParams: WorkerParameters): ListenableWorker {
            return SubmissionWorker(
                appContext,
                workerParams,
                mFirebaseHelper.get(),
                mDatabase.get()
            )
        }

    }
}