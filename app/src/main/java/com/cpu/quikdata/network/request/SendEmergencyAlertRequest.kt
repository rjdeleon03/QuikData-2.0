package com.cpu.quikdata.network.request

data class SendEmergencyAlertRequest(val notification: Notification,
                                     val priority: String,
                                     val to: String) {
    companion object {
        fun createWithTextOnly(text: String): SendEmergencyAlertRequest {
            return SendEmergencyAlertRequest(Notification("Emergency Alert", text),
                "high",
                "/topics/emergency")
        }
    }
}


data class Notification(val title: String, val text: String)