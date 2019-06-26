package com.cpu.quikdata.data.formdetails

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "form_details",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FormDetails(@PrimaryKey(autoGenerate = false)
                       override val id: String = "",
                       var assessmentDate: Long = 0L,
                       var interviewer: String = "",
                       var interviewerContact: String = "",
                       var interviewee: String = "",
                       var intervieweeContact: String = "",
                       var sourcesOfInformation: String = "",
                       val formId: String = "") : BaseDataWithId {

    fun copyFrom(formDetails: FormDetails) {
        assessmentDate = formDetails.assessmentDate
        interviewer = formDetails.interviewer
        interviewerContact = formDetails.interviewerContact
        interviewee = formDetails.interviewee
        intervieweeContact = formDetails.intervieweeContact
        sourcesOfInformation = formDetails.sourcesOfInformation
    }
}