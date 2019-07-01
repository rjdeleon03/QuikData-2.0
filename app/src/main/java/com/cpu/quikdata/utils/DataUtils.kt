package com.cpu.quikdata.utils

import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.util.*

fun generateId(): String {
    return UUID.randomUUID().toString();
}

fun getDateNowInLong(): Long = LocalDate.now().toDateTimeAtStartOfDay().millis

fun getDateTimeNowInLong(): Long = LocalDateTime.now().toDateTime().millis