package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.utils.runOnIoThread

class BaselineDataRepository(application: Application, formId: String) :
    BaseRepository<BaselineData>(application) {

    private val mBaselineData = mDatabase.baselineDataDao().getByFormId(formId)
    private val mPrefilledData = MediatorLiveData<PrefilledData>()

    val baselineData : LiveData<BaselineData>
        get() = mBaselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mPrefilledData

    override fun updateData(data: BaselineData) {
        runOnIoThread {
            val oldBaselineData = mBaselineData.value!!
            oldBaselineData.copyFrom(data)
            mDatabase.baselineDataDao().update(oldBaselineData)
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