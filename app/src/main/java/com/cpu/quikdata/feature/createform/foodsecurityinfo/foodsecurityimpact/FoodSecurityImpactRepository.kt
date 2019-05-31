package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityImpactRepository(application: Application, formId: String) :
    BaseRepository<FoodSecurityImpact>(application) {

    private val mImpact = mDatabase.foodSecurityImpactDao().getByFormId(formId)

    val impact : LiveData<FoodSecurityImpact>
        get() = mImpact

    override fun updateData(data: FoodSecurityImpact) {
        runOnIoThread {
            val oldImpact = mImpact.value!!
            oldImpact.copyFrom(data)
            mDatabase.foodSecurityImpactDao().update(oldImpact)
        }
    }
}