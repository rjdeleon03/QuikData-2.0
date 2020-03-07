package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import javax.inject.Inject

class BaselineDataRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String
) {

    private val mBaselineData = mDatabase.baselineDataDao().getByFormId(mFormId)
    private val mPrefilledData = MediatorLiveData<PrefilledData>()

    val baselineData: LiveData<BaselineData>
        get() = mBaselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mPrefilledData

    suspend fun updateData(data: BaselineData) {
        mBaselineData.value?.apply {
            copyFrom(data)
            mDatabase.baselineDataDao().update(this)
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