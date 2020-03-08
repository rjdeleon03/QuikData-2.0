package com.cpu.quikdata.data

import androidx.room.TypeConverter
import com.cpu.quikdata.data.form.FormStatus

class Converters {

    @TypeConverter
    fun intToFormStatus(intValue: Int): FormStatus {
        return FormStatus.values()[intValue]
    }

    @TypeConverter
    fun formStatusToInt(formStatus: FormStatus): Int {
        return formStatus.ordinal
    }

}