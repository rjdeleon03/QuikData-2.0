package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.FIREBASE_KEY_FORM
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase

class CreateFormRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mForm = mDatabase.formDao().getById(formId)
    private val mServerRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_FORM)

    val form: LiveData<Form>
        get() = mForm

    fun submitData() {
        runOnIoThread {

            // Retrieve form from database
            val dao = mDatabase.formDao()
            val form = mForm.value!!

            // Save to server
            val pushRef = if (!form.idRemote.isEmpty()) {
                mServerRef.child(form.idRemote)
            } else {
                mServerRef.push()
            }

            val task = pushRef.setValue(form)
            task.addOnCompleteListener {
                if (form.idRemote.isEmpty()) {
                    runOnIoThread {

                        // Upon successful save, update form with server ID
                        val key = pushRef.key
                        if (key != null) {
                            form.idRemote = key
                            dao.update(form)
                        }
                    }
                }
            }
        }
    }
}