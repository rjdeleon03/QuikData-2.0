package com.cpu.quikdata.data.shelterinfo.sheltergaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "shelter_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class ShelterGaps(@PrimaryKey(autoGenerate = false)
                       override val id: String = "",
                       var cubicles: String = "",
                       var culturalPracticeAssistance: String = "",
                       var shelterAppropriate: String = "",
                       var servicesAccess: String = "",
                       var anyAbleBodied: String = "",
                       var gbvReferralPathway: String = "",
                       var gbvProtectionServices: String = "",
                       var gbvProtectionFocalPoint: String = "",
                       var formIdRemote: String = "",
                       val formId: String = "") : BaseDataWithId {

    fun copyFrom(shelterGaps: ShelterGaps) {
        cubicles = shelterGaps.cubicles
        culturalPracticeAssistance = shelterGaps.culturalPracticeAssistance
        shelterAppropriate = shelterGaps.shelterAppropriate
        servicesAccess = shelterGaps.servicesAccess
        anyAbleBodied = shelterGaps.anyAbleBodied
        gbvReferralPathway = shelterGaps.gbvReferralPathway
        gbvProtectionServices = shelterGaps.gbvProtectionServices
        gbvProtectionFocalPoint = shelterGaps.gbvProtectionFocalPoint
        formIdRemote = shelterGaps.formIdRemote
    }
}