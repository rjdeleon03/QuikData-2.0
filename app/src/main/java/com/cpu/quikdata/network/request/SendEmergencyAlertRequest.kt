package com.cpu.quikdata.network.request

data class SendEmergencyAlertRequest(val notification: Notification,
                                     val priority: String,
                                     val to: String)


data class Notification(val title: String, val text: String)