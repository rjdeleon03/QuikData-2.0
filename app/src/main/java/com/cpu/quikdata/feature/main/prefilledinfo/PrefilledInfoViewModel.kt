package com.cpu.quikdata.feature.main.prefilledinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.feature.QuikDataApp

class PrefilledInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = PrefilledInfoRepository(application as QuikDataApp)

    val prefilledData: LiveData<PrefilledData>
        get() = mRepository.prefilledData

    fun updatePrefilledData(prefilledData: PrefilledData) =
        mRepository.updatePrefilledData(prefilledData)
}
