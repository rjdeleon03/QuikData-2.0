package com.cpu.quikdata.data.generalinfo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "general_info",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE)])
data class GeneralInfo(@PrimaryKey(autoGenerate = false)
                       val id: String = "",
                       val formId: String = "")