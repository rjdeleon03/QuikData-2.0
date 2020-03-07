package com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.formdetails.FormDetails
import javax.inject.Inject

class FormDetailsRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String
) {

    private val mFormDetails = mDatabase.formDetailsDao().getByFormId(mFormId)

    val formDetails: LiveData<FormDetails>
        get() = mFormDetails

    suspend fun updateData(data: FormDetails) {
        mFormDetails.value?.apply {
            copyFrom(data)
            mDatabase.formDetailsDao().update(this)
        }
    }
}