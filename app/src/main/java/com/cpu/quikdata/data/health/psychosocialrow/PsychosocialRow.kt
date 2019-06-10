package com.cpu.quikdata.data.health.psychosocialrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "psychosocial_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class PsychosocialRow(@PrimaryKey(autoGenerate = false)
                           val id: String = "",
                           val type: Int = 0,
                           var casesMale: Int = 0,
                           var casesFemale: Int = 0,
                           var manifestations: String = "",
                           var needs: String = "",
                           var formIdRemote: String = "",
                           val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is PsychosocialRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            casesMale == other.casesMale &&
            casesFemale == other.casesFemale &&
            manifestations == other.manifestations &&
            needs == other.needs &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}