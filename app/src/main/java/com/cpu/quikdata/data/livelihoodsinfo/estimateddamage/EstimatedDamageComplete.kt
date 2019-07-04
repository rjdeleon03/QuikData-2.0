package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.room.*

class EstimatedDamageComplete {
    @Embedded
    var row: EstimatedDamageRow? = null

    @Relation(parentColumn = "id",
        entityColumn = "estimatedDamageId",
        entity = EstimatedDamageType::class)
    var types: List<EstimatedDamageType>? = null

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is EstimatedDamageComplete) return false
        var areTypesEqual = false
        if (this.types == null && other.types == null) {
            areTypesEqual = true
        } else if (this.types != null && other.types != null) {
            if (this.types!!.size != other.types!!.size) areTypesEqual = false
            else {
                for (i in 0 until this.types!!.size) {
                    if (this.types!![i] != other.types!![i]){
                        areTypesEqual = false
                        break
                    }
                }
            }
        }

        return areTypesEqual && row == other
    }
}