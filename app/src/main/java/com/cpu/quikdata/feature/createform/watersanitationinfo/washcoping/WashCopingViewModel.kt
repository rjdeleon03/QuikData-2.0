package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping

class WashCopingViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = WashCopingRepository(application, formId)

    val washCoping : LiveData<WashCoping>
        get() = mRepository.washCoping

    fun updateWashCoping(washCoping: WashCoping) =
        mRepository.updateData(washCoping)
}
