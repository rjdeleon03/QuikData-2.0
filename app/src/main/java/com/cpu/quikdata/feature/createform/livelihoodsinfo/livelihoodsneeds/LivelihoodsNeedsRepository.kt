package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds

class LivelihoodsNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<LivelihoodsNeeds>() {

    private val mLivelihoodsNeeds = mDatabase.livelihoodsNeedsDao().getByFormId(formId)

    val livelihoodsNeeds : LiveData<LivelihoodsNeeds>
        get() = mLivelihoodsNeeds

    override suspend fun updateData(data: LivelihoodsNeeds) {
        mLivelihoodsNeeds.value?.let {
            it.copyFrom(data)
            mDatabase.livelihoodsNeedsDao().update(it)
        }
    }
}