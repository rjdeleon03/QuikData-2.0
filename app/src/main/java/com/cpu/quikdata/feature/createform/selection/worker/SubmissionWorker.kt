package com.cpu.quikdata.feature.createform.selection.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.cpu.quikdata.R
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.common.toDateTimeString
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.di.app.module.ChildWorkerFactory
import com.cpu.quikdata.utils.getDateTimeNowInLong
import java.lang.Exception
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Provider


class SubmissionWorker(
    context: Context, workerParams: WorkerParameters,
    private val mFirebaseHelper: FirebaseHelper,
    private val mDatabase: AppDatabase
) :
    CoroutineWorker(context, workerParams) {

    companion object {
        const val QUIK_DATA_NOTIF_CHANNEL = "quik_data"
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
            createProgressNotification(form)

            val isBasicMode = inputData.getBoolean(MODE_KEY, true)
            if (isBasicMode) {
                mFirebaseHelper.sendBasicData(form.id) {
                    createResultNotification(form)
                }
            } else {
                mFirebaseHelper.sendAllData(form.id) {
                    createResultNotification(form)
                }
            }
        }
        return Result.success()
    }

    private suspend fun retrieveFormAndSaveAsNonTemporary(formId: String): Form {
        return mDatabase.formDao().getByIdNonLive(formId).apply {
            isTemporary = false
            dateModified = getDateTimeNowInLong()
            mDatabase.formDao().update(this)
        }
    }

    private fun createProgressNotification(form: Form) {
        val manager = getNotificationManager()

        val notif = NotificationCompat.Builder(applicationContext, QUIK_DATA_NOTIF_CHANNEL)
            .setContentTitle("Submitting DNCA Form")
            .setContentText("Form created at ${form.dateCreated.toDateTimeString()}")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setProgress(0, 0, true)
            .setOngoing(true)
        manager.notify(form.id, 1, notif.build())
    }

    private fun createResultNotification(form: Form) {
        val manager = getNotificationManager()

        val notif = NotificationCompat.Builder(applicationContext, QUIK_DATA_NOTIF_CHANNEL)
            .setContentTitle("DNCA Form Submitted")
            .setContentText("Form created at ${form.dateCreated.toDateTimeString()}")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(false)
        manager.notify(form.id, 1, notif.build())
    }

    private fun getNotificationManager(): NotificationManager {
        val manager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                QUIK_DATA_NOTIF_CHANNEL,
                QUIK_DATA_NOTIF_CHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        return manager
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