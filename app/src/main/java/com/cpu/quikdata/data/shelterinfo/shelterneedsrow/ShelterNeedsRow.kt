package com.cpu.quikdata.data.shelterinfo.shelterneedsrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "shelter_needs_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class ShelterNeedsRow(@PrimaryKey(autoGenerate = false)
                            val id: String = "",
                           val type: Int = 0,
                           var specificItems: String = "",
                           var familiesInNeed: Int = 0,
                           var formIdRemote: String = "",
                           val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ShelterNeedsRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            specificItems == other.specificItems &&
            familiesInNeed == other.familiesInNeed &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}