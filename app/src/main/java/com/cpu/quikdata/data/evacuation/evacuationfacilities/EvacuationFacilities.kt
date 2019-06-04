package com.cpu.quikdata.data.evacuation.evacuationfacilities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_facilities",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationFacilities(@PrimaryKey(autoGenerate = false)
                                val id: String = "",
                                var capacity: Int = 0,
                                var toilet: Boolean = true,
                                var toiletRemarks: String = "",
                                var wideEntrance: Boolean = true,
                                var wideEntranceRemarks: String = "",
                                var electricity: Boolean = true,
                                var electricityRemarks: String = "",
                                var waterSupply: Boolean = true,
                                var waterSupplyRemarks: String = "",
                                var ventilation: Boolean = true,
                                var ventilationRemarks: String = "",
                                val evacuationId: String = "") {

    fun copyFrom(evacuationFacilities: EvacuationFacilities) {
        capacity = evacuationFacilities.capacity
        toilet = evacuationFacilities.toilet
        toiletRemarks = evacuationFacilities.toiletRemarks
        wideEntrance = evacuationFacilities.wideEntrance
        wideEntranceRemarks = evacuationFacilities.wideEntranceRemarks
        electricity = evacuationFacilities.electricity
        electricityRemarks = evacuationFacilities.electricityRemarks
        waterSupply = evacuationFacilities.waterSupply
        waterSupplyRemarks = evacuationFacilities.waterSupplyRemarks
        ventilation = evacuationFacilities.ventilation
        ventilationRemarks = evacuationFacilities.ventilationRemarks
    }
}