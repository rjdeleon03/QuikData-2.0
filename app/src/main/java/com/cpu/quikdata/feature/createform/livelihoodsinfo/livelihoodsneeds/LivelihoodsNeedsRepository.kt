package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class LivelihoodsNeedsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mLivelihoodsNeeds = mDatabase.livelihoodsNeedsDao().getByFormId(formId)

    val livelihoodsNeeds : LiveData<LivelihoodsNeeds>
        get() = mLivelihoodsNeeds

    fun updateData(data: LivelihoodsNeeds) {
        runOnIoThread {
            val oldNeeds = mLivelihoodsNeeds.value!!
            oldNeeds.copyFrom(data)
            mDatabase.livelihoodsNeedsDao().update(oldNeeds)
        }
    }
}