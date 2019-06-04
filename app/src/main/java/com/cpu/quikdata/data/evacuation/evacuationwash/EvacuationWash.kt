package com.cpu.quikdata.data.evacuation.evacuationwash

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_wash",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationWash(@PrimaryKey(autoGenerate = false)
                          val id: String = "",
                          var foodPreparation: Boolean = true,
                          var foodPreparationRemarks: String = "",
                          var waterSource: Boolean = true,
                          var waterSourceRemarks: String = "",
                          var toiletBaths: Boolean = true,
                          var toiletBathsRemarks: String = "",
                          var garbageDisposal: Boolean = true,
                          var garbageDisposalRemarks: String = "",
                          var clinic: Boolean = true,
                          var clinicRemarks: String = "",
                          var sick: Boolean = true,
                          var sickRemarks: String = "",
                          var handWashing: Boolean = true,
                          var handWashingRemarks: String = "",
                          val evacuationId: String = "") {

    fun copyFrom(evacuationFacilities: EvacuationWash) {
        foodPreparation = evacuationFacilities.foodPreparation
        foodPreparationRemarks = evacuationFacilities.foodPreparationRemarks
        waterSource = evacuationFacilities.waterSource
        waterSourceRemarks = evacuationFacilities.waterSourceRemarks
        toiletBaths = evacuationFacilities.toiletBaths
        toiletBathsRemarks = evacuationFacilities.toiletBathsRemarks
        garbageDisposal = evacuationFacilities.garbageDisposal
        garbageDisposalRemarks = evacuationFacilities.garbageDisposalRemarks
        clinic = evacuationFacilities.clinic
        clinicRemarks = evacuationFacilities.clinicRemarks
        sick = evacuationFacilities.sick
        sickRemarks = evacuationFacilities.sickRemarks
        handWashing = evacuationFacilities.handWashing
        handWashingRemarks = evacuationFacilities.handWashingRemarks
    }
}