package com.cpu.quikdata.data.formdetails

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "form_details",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FormDetails(@PrimaryKey(autoGenerate = false)
                       val id: String,
                       val assessmentDate: Long = 0L,
                       val interviewer: String = "",
                       val interviewerContact: String = "",
                       val interviewee: String = "",
                       val intervieweeContact: String = "",
                       val sourcesOfInformation: String = "",
                       val formId: String)