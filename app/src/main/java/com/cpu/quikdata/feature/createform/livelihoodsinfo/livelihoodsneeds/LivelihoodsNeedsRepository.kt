package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import com.cpu.quikdata.utils.runOnIoThread

class LivelihoodsNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<LivelihoodsNeeds>() {

    private val mLivelihoodsNeeds = mDatabase.livelihoodsNeedsDao().getByFormId(formId)

    val livelihoodsNeeds : LiveData<LivelihoodsNeeds>
        get() = mLivelihoodsNeeds

    override fun updateData(data: LivelihoodsNeeds) {
        runOnIoThread {
            val oldNeeds = mLivelihoodsNeeds.value!!
            oldNeeds.copyFrom(data)
            mDatabase.livelihoodsNeedsDao().update(oldNeeds)
        }
    }
}