package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import javax.inject.Inject

class LivelihoodsNeedsRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mLivelihoodsNeeds = mDatabase.livelihoodsNeedsDao().getByFormId(formId)

    val livelihoodsNeeds: LiveData<LivelihoodsNeeds>
        get() = mLivelihoodsNeeds

    suspend fun updateData(data: LivelihoodsNeeds) {
        mLivelihoodsNeeds.value?.apply {
            copyFrom(data)
            mDatabase.livelihoodsNeedsDao().update(this)
        }
    }
}