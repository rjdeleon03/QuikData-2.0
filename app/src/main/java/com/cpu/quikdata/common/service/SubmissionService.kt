package com.cpu.quikdata.common.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.helper.FirebaseHelper
import com.cpu.quikdata.common.toDateTimeString
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormStatus
import com.cpu.quikdata.feature.createform.selection.worker.SubmissionWorker
import javax.inject.Inject

class SubmissionService: Service() {

    companion object {
        const val FORM_ID_KEY = "FORM_ID_KEY"
    }

    @Inject lateinit var mFirebaseHelper: FirebaseHelper
    @Inject lateinit var mDatabase: AppDatabase

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_REDELIVER_INTENT;
    }

    private fun createProgressNotification(form: Form) {
        val manager = getNotificationManager()

        val notif = NotificationCompat.Builder(applicationContext,
            SubmissionWorker.QUIK_DATA_NOTIF_CHANNEL
        )
            .setContentTitle("Submitting DNCA Form")
            .setContentText("Form created at ${form.dateCreated.toDateTimeString()}")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setProgress(0, 0, true)
            .setOngoing(true)
        manager.notify(form.id, 1, notif.build())
    }

    private fun createResultNotification(form: Form, status: FormStatus) {
        val manager = getNotificationManager()

        val title = when (status) {
            FormStatus.SUBMITTED -> "DNCA Form Submitted"
            else -> "Failed to Submit DNCA Form"
        }

        val notif = NotificationCompat.Builder(applicationContext,
            SubmissionWorker.QUIK_DATA_NOTIF_CHANNEL
        )
            .setContentTitle(title)
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
                SubmissionWorker.QUIK_DATA_NOTIF_CHANNEL,
                applicationContext.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        return manager
    }
}