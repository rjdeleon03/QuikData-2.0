package com.cpu.quikdata.network

import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import com.cpu.quikdata.network.response.SendEmergencyAlertResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseMessagingApi {

    @POST("send")
    fun sendEmergencyAlert(@Body request: SendEmergencyAlertRequest):
            Deferred<SendEmergencyAlertResponse>
}