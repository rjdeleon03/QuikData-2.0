package com.cpu.quikdata.data.casestories

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "case_stories",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CaseStories(@PrimaryKey(autoGenerate = false)
                       val id: String = "",
                       var text: String = "",
                       val formId: String = "") {

    fun copyFrom(caseStories: CaseStories) {
        text = caseStories.text
    }
}