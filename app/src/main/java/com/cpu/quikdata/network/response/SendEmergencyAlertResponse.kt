package com.cpu.quikdata.network.response

import com.google.gson.annotations.SerializedName

data class SendEmergencyAlertResponse(

    @SerializedName("message_id")
    val messageId: String? = "")