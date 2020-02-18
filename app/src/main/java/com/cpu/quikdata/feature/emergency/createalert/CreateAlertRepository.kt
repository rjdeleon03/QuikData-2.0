package com.cpu.quikdata.feature.emergency.createalert

import com.cpu.quikdata.network.FirebaseMessagingApi
import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import com.cpu.quikdata.network.response.SendEmergencyAlertResponse

class CreateAlertRepository(private val mFirebaseMessagingApi: FirebaseMessagingApi) {

    suspend fun sendAlert(request: SendEmergencyAlertRequest): SendEmergencyAlertResponse {

        val result = mFirebaseMessagingApi.sendEmergencyAlert(request)
        val data = result.await()
        return data
    }
}