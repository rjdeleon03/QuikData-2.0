package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.prefilleddata.PrefilledData

class BaselineDataRepository(private val mDatabase: AppDatabase, val formId: String) {

    private val mBaselineData = mDatabase.baselineDataDao().getByFormId(formId)
    private val mPrefilledData = MediatorLiveData<PrefilledData>()

    val baselineData : LiveData<BaselineData>
        get() = mBaselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mPrefilledData

    suspend fun updateData(data: BaselineData) {
        mBaselineData.value?.let {
            it.copyFrom(data)
            mDatabase.baselineDataDao().update(it)
        }
    }

    fun pullPrefilledData() {
        val source = mDatabase.prefilledDataDao().get()
        mPrefilledData.addSource(source) {
            mPrefilledData.value = it
            mPrefilledData.removeSource(source)
        }
    }
}