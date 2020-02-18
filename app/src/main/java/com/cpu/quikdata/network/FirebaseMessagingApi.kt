package com.cpu.quikdata.network

import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import com.cpu.quikdata.network.response.SendEmergencyAlertResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseMessagingApi {

    @POST("send")
    suspend fun sendEmergencyAlert(@Body request: SendEmergencyAlertRequest): SendEmergencyAlertResponse
}