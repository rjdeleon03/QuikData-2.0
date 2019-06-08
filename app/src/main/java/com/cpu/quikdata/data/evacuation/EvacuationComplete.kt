package com.cpu.quikdata.data.evacuation

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash

class EvacuationComplete {

    @Embedded
    var root: EvacuationItem? = null

    @Relation(parentColumn = "id",
        entityColumn = "evacuationId",
        entity = EvacuationAgeRow::class)
    var breakdown: List<EvacuationAgeRow>? = null

    @Relation(parentColumn = "id",
        entityColumn = "evacuationId",
        entity = EvacuationFacilities::class)
    var facilities: List<EvacuationFacilities>? = null

    @Relation(parentColumn = "id",
        entityColumn = "evacuationId",
        entity = EvacuationWash::class)
    var wash: List<EvacuationWash>? = null

    @Relation(parentColumn = "id",
        entityColumn = "evacuationId",
        entity = EvacuationProtection::class)
    var protection: List<EvacuationProtection>? = null

    @Relation(parentColumn = "id",
        entityColumn = "evacuationId",
        entity = EvacuationCoping::class)
    var coping: List<EvacuationCoping>? = null
}