package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.CoroutineContextProvider
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import javax.inject.Inject

class PrefilledInfoViewModel @Inject constructor(
    private val mRepository: PrefilledInfoRepository,
    private val mContextProvider: CoroutineContextProvider)
    : ViewModel() {

    val prefilledData: LiveData<PrefilledData>
        get() = mRepository.prefilledData

    fun updatePrefilledData(prefilledData: PrefilledData) =
        runOnIoThread(mContextProvider) {
            mRepository.updatePrefilledData(prefilledData)
        }
}
