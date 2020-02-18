package com.cpu.quikdata.feature.emergency.createalert

import com.cpu.quikdata.network.FirebaseMessagingApi
import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import com.cpu.quikdata.network.response.SendEmergencyAlertResponse
import java.lang.Exception

class CreateAlertRepository(private val mFirebaseMessagingApi: FirebaseMessagingApi) {

    suspend fun sendAlert(request: SendEmergencyAlertRequest): SendEmergencyAlertResponse {

        return try {
            mFirebaseMessagingApi.sendEmergencyAlert(request)
        } catch (ex: Exception) {
            SendEmergencyAlertResponse()
        }
    }
}