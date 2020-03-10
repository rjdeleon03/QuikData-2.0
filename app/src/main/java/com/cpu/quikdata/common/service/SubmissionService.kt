package com.cpu.quikdata.common.service

import android.app.IntentService
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
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.feature.createform.selection.worker.SubmissionWorker
import com.cpu.quikdata.utils.getDateTimeNowInLong
import kotlinx.coroutines.*
import javax.inject.Inject

class SubmissionService : IntentService("SubmissionService") {

    companion object {
        private const val FORM_ID_KEY = "FORM_ID_KEY"
        private const val MODE_KEY = "MODE_KEY"

        @JvmStatic
        fun newInstance(context: Context, formId: String, isBasicMode: Boolean = false) {
            val serviceIntent = Intent(context, SubmissionService::class.java).apply {
                putExtra(FORM_ID_KEY, formId)
                putExtra(MODE_KEY, isBasicMode)
            }
            context.startService(serviceIntent)
        }
    }

    @Inject
    lateinit var mFirebaseHelper: FirebaseHelper
    @Inject
    lateinit var mDatabase: AppDatabase

    private val mServiceIoScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        (application as QuikDataApp).appComponent
            .submissionServiceComponent().create(this).inject(this)
    }

    override fun onDestroy() {
        mServiceIoScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        // There is no need to bind the service.
        return null
    }

    override fun onHandleIntent(p0: Intent?) {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra(FORM_ID_KEY)?.let { formId ->

            mServiceIoScope.launch {
                val form = retrieveFormAndSaveAsNonTemporary(formId)
                createProgressNotification(form)

                val isBasicMode = intent.getBooleanExtra(MODE_KEY, false)
                if (isBasicMode) {
                    mFirebaseHelper.sendBasicData(form.id) {
                        createResultNotification(form, it)
                    }
                } else {
                    mFirebaseHelper.sendAllData(form.id) {
                        createResultNotification(form, it)
                    }
                }
            }


        }
        return Service.START_REDELIVER_INTENT;
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

        val notif = NotificationCompat.Builder(
            applicationContext,
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

        val notif = NotificationCompat.Builder(
            applicationContext,
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