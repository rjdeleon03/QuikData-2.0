package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import com.cpu.quikdata.utils.runOnIoThread

class LivelihoodsNeedsRepository(application: Application, formId: String) :
    BaseRepository<LivelihoodsNeeds>(application) {

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