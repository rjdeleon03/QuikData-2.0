package com.cpu.quikdata.data.generalinfo.causeofdeath

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "cause_of_death_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CauseOfDeathRow(@PrimaryKey(autoGenerate = false)
                           val id: String = "",
                           val type: Int = 0,
                           var measlesMale: Int = 0,
                           var measlesFemale: Int = 0,
                           var diarrheaMale: Int = 0,
                           var diarrheaFemale: Int = 0,
                           var pneumoniaMale: Int = 0,
                           var pneumoniaFemale: Int = 0,
                           var dengueMale: Int = 0,
                           var dengueFemale: Int = 0,
                           var drowningMale: Int = 0,
                           var drowningFemale: Int = 0,
                           var heartAttackMale: Int = 0,
                           var heartAttackFemale: Int = 0,
                           var electrocutionMale: Int = 0,
                           var electrocutionFemale: Int = 0,
                           var collapsedBldgMale: Int = 0,
                           var collapsedBldgFemale: Int = 0,
                           var othersMale: Int = 0,
                           var othersFemale: Int = 0,
                           val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CauseOfDeathRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            measlesMale == other.measlesMale &&
            measlesFemale == other.measlesFemale &&
            diarrheaMale == other.diarrheaMale &&
            diarrheaFemale == other.diarrheaFemale &&
            pneumoniaMale == other.pneumoniaMale &&
            pneumoniaFemale == other.pneumoniaFemale &&
            dengueMale == other.dengueMale &&
            dengueFemale == other.dengueFemale &&
            drowningMale == other.drowningMale &&
            drowningFemale == other.drowningFemale &&
            heartAttackMale == other.heartAttackMale &&
            heartAttackFemale == other.heartAttackFemale &&
            electrocutionMale == other.electrocutionMale &&
            electrocutionFemale == other.electrocutionFemale &&
            collapsedBldgMale == other.collapsedBldgMale &&
            collapsedBldgFemale == other.collapsedBldgFemale &&
            othersMale == other.othersMale &&
            othersFemale == other.othersFemale &&
            formId == other.formId)
            return true
        return false
    }
}