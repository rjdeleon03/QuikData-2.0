package com.cpu.quikdata.data.evacuation.evacuationwash

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_wash",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationWash(@PrimaryKey(autoGenerate = false)
                          override val id: String = "",
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
                          val evacuationId: String = "") : BaseDataWithId {

    fun copyFrom(evacuationWash: EvacuationWash) {
        foodPreparation = evacuationWash.foodPreparation
        foodPreparationRemarks = evacuationWash.foodPreparationRemarks
        waterSource = evacuationWash.waterSource
        waterSourceRemarks = evacuationWash.waterSourceRemarks
        toiletBaths = evacuationWash.toiletBaths
        toiletBathsRemarks = evacuationWash.toiletBathsRemarks
        garbageDisposal = evacuationWash.garbageDisposal
        garbageDisposalRemarks = evacuationWash.garbageDisposalRemarks
        clinic = evacuationWash.clinic
        clinicRemarks = evacuationWash.clinicRemarks
        sick = evacuationWash.sick
        sickRemarks = evacuationWash.sickRemarks
        handWashing = evacuationWash.handWashing
        handWashingRemarks = evacuationWash.handWashingRemarks
    }
}