package com.cpu.quikdata.data.formdetails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_details")
data class FormDetails(@PrimaryKey(autoGenerate = false)
                       val id: String,
                       val assessmentDate: Long,
                       val interviewer: String,
                       val interviewerContact: String,
                       val interviewee: String,
                       val intervieweeContact: String,
                       val sourcesOfInformation: String)