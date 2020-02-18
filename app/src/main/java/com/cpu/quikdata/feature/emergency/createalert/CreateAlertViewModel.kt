package com.cpu.quikdata.feature.emergency.createalert

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import com.cpu.quikdata.network.response.SendEmergencyAlertResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateAlertViewModel @Inject constructor(private val mRepository: CreateAlertRepository)
    : ViewModel() {

    val sendAlertResult = MutableLiveData<Boolean>()

    val loading = MutableLiveData<Boolean>()

    fun sendAlert(request: SendEmergencyAlertRequest) {

        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = mRepository.sendAlert(request)

            val isSuccessful = !result.messageId.isNullOrBlank()
            loading.postValue(false)
            sendAlertResult.postValue(isSuccessful)
        }

    }
}