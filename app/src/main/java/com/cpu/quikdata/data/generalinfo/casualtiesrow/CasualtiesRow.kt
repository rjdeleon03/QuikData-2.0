package com.cpu.quikdata.data.generalinfo.casualtiesrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "casualties_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CasualtiesRow(@PrimaryKey(autoGenerate = false)
                         override val id: String = "",
                         val type: Int = 0,
                         var deadMale: Int = 0,
                         var deadFemale: Int = 0,
                         var missingMale: Int = 0,
                         var missingFemale: Int = 0,
                         var injuredMale: Int = 0,
                         var injuredFemale: Int = 0,
                         var formIdRemote: String = "",
                         val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CasualtiesRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            deadMale == other.deadMale &&
            deadFemale == other.deadFemale &&
            missingMale == other.missingMale &&
            missingFemale == other.missingFemale &&
            injuredMale == other.injuredMale &&
            injuredFemale == other.injuredFemale &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}