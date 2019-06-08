package com.cpu.quikdata.feature.createform

import android.app.Application
import com.cpu.quikdata.FIREBASE_KEY_FORM
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase

class CreateFormRepository(application: Application) {

    private val mDatabase = AppDatabase.get(application)
    private val mServerRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_FORM)


    fun submitData(id: String) {
        runOnIoThread {

            // Retrieve form from database
            val dao = mDatabase.formDao()
            val form = dao.getByIdSingle(id)

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
                        System.out.println("KEY: ${pushRef.key}")

                        if (pushRef.key != null) {
                            form.idRemote = pushRef.key!!
                            dao.update(form)
                        }
                    }
                }
            }
        }
    }
}