package com.cpu.quikdata.feature.createform.formdetails

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.formdetails.FormDetails

class FormDetailsRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFormDetails = mDatabase.formDetailsDao().getByFormId(formId)

    val formDetails : LiveData<FormDetails>
        get() = mFormDetails
}