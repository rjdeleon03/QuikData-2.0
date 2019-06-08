package com.cpu.quikdata.data.watersanitationinfo.washgaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "wash_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class WashGaps(@PrimaryKey(autoGenerate = false)
                    val id: String = "",
                    var assistanceEnough: String = "",
                    var assistanceRelevant: String = "",
                    var waterSourceAccessible: String = "",
                    var reliefConsultation: String = "",
                    var wasteManagement: String = "",
                    var attitudes: String = "",
                    var supportMechanisms: String = "",
                    var womenParticipation: String = "",
                    var formIdRemote: String = "",
                    val formId: String = "") {

    fun copyFrom(washGaps: WashGaps) {
        assistanceEnough = washGaps.assistanceEnough
        assistanceRelevant = washGaps.assistanceRelevant
        waterSourceAccessible = washGaps.waterSourceAccessible
        reliefConsultation = washGaps.reliefConsultation
        wasteManagement = washGaps.wasteManagement
        attitudes = washGaps.attitudes
        supportMechanisms = washGaps.supportMechanisms
        womenParticipation = washGaps.womenParticipation
        formIdRemote = washGaps.formIdRemote
    }
}