package com.cpu.quikdata.feature.main.newforms

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.utils.generateId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime

class NewFormsRepository(application: Application) {

    private val mDatabase = AppDatabase.get(application)
    private val mNewForms = mDatabase.formDao().getAll()

    val newForms: LiveData<List<Form>>
        get() = mNewForms

    fun createNewForm() {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            val id = generateId()
            val form = Form(id)
            mDatabase.formDao().insert(form)

            val formDetails = FormDetails(id = generateId(),
                assessmentDate = DateTime().millis,
                formId = id)
            mDatabase.formDetailsDao().insert(formDetails)
        }
    }


}