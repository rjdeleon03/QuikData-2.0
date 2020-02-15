package com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.utils.runOnIoThread

class FormDetailsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FormDetails>() {

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