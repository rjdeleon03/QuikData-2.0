package com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FormDetailsRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String)  {

    private val mFormDetails = mDatabase.formDetailsDao().getByFormId(mFormId)

    val formDetails : LiveData<FormDetails>
        get() = mFormDetails

    fun updateData(data: FormDetails) {
        runOnIoThread {
            mFormDetails.value?.let { oldFormDetails ->
                oldFormDetails.copyFrom(data)
                mDatabase.formDetailsDao().update(oldFormDetails)
            }
        }
    }
}