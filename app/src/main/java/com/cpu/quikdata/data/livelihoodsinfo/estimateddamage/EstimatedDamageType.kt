package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "estimated_damage_type",
    indices = [Index("estimatedDamageId")],
    foreignKeys = [ForeignKey(entity = EstimatedDamageRow::class,
        parentColumns = ["id"],
        childColumns = ["estimatedDamageId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EstimatedDamageType(@PrimaryKey(autoGenerate = false)
                               val id: String = "",
                               var type: Int = 0,
                               var isSelected: Boolean = false,
                               val estimatedDamageId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is EstimatedDamageType) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            isSelected == other.isSelected &&
            estimatedDamageId == other.estimatedDamageId)
            return true
        return false
    }
}