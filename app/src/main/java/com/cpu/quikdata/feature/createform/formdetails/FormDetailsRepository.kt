package com.cpu.quikdata.feature.createform.formdetails

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.utils.runOnIoThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class FormDetailsRepository(application: Application, formId: String) :
    BaseRepository<FormDetails>(application) {

    private val mFormDetails = mDatabase.formDetailsDao().getByFormId(formId)

    val formDetails : LiveData<FormDetails>
        get() = mFormDetails

    override fun updateData(data: FormDetails) {
        runOnIoThread {
            val oldFormDetails = mFormDetails.value!!
            oldFormDetails.copyFrom(data)
            mDatabase.formDetailsDao().update(oldFormDetails)
        }
    }
}