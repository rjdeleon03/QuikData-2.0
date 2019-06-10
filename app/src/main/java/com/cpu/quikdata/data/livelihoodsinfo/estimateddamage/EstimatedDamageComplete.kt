package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.room.*

class EstimatedDamageComplete {
    @Embedded
    var row: EstimatedDamageRow? = null

    @Relation(parentColumn = "id",
        entityColumn = "estimatedDamageId",
        entity = EstimatedDamageType::class)
    var types: List<EstimatedDamageType>? = null
}