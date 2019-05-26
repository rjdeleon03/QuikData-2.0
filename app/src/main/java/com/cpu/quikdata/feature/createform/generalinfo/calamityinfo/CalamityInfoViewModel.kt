package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo

class CalamityInfoViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = CalamityInfoRepository(application, formId)

    val calamityInfo: LiveData<CalamityInfo>
        get() = mRepository.calamityInfo

}
