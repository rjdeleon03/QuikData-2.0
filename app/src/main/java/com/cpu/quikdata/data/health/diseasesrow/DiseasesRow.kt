package com.cpu.quikdata.data.health.diseasesrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "diseases_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class DiseasesRow(@PrimaryKey(autoGenerate = false)
                       val id: String = "",
                       val type: Int = 0,
                       var diarrheaMale: Int = 0,
                       var diarrheaFemale: Int = 0,
                       var pneumoniaMale: Int = 0,
                       var pneumoniaFemale: Int = 0,
                       var dengueMale: Int = 0,
                       var dengueFemale: Int = 0,
                       var measlesMale: Int = 0,
                       var measlesFemale: Int = 0,
                       var othersMale: Int = 0,
                       var othersFemale: Int = 0,
                       var checkUpMale: Int = 0,
                       var checkUpFemale: Int = 0,
                       var hospitalizationMale: Int = 0,
                       var hospitalizationFemale: Int = 0,
                       var medicinesMale: Int = 0,
                       var medicinesFemale: Int = 0,
                       var medicalOthersMale: Int = 0,
                       var medicalOthersFemale: Int = 0,
                       var formIdRemote: String = "",
                       val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is DiseasesRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            diarrheaMale == other.diarrheaMale &&
            diarrheaFemale == other.diarrheaFemale &&
            pneumoniaMale == other.pneumoniaMale &&
            pneumoniaFemale == other.pneumoniaFemale &&
            dengueMale == other.dengueMale &&
            dengueFemale == other.dengueFemale &&
            measlesMale == other.measlesMale &&
            measlesFemale == other.measlesFemale &&
            othersMale == other.othersMale &&
            othersFemale == other.othersFemale &&
            checkUpMale == other.checkUpMale &&
            checkUpFemale == other.checkUpFemale &&
            hospitalizationMale == other.hospitalizationMale &&
            hospitalizationFemale == other.hospitalizationFemale &&
            medicinesMale == other.medicinesMale &&
            medicinesFemale == other.medicinesFemale &&
            medicalOthersMale == other.medicalOthersMale &&
            medicalOthersFemale == other.medicalOthersFemale &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}