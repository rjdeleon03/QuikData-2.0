package com.cpu.quikdata.data.generalinfo.calamityinfo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "calamity_info",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CalamityInfo(@PrimaryKey(autoGenerate = false)
                        val id: String = "",
                        var calamityType: String = "",
                        var occurrenceDate: Long = 0L,
                        var eventDescription: String = "",
                        var affectedAreaDescription: String = "",
                        val formId: String = "")