package com.cpu.quikdata.data.health.specialneedsrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "special_needs_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class SpecialNeedsRow(@PrimaryKey(autoGenerate = false)
                           val id: String = "",
                           val type: Int = 0,
                           var number: Int = 0,
                           var healthMedical: String = "",
                           val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SpecialNeedsRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            number == other.number &&
            healthMedical == other.healthMedical &&
            formId == other.formId)
            return true
        return false
    }
}