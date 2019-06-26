package com.cpu.quikdata.data.evacuation.evacuationagerow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_age_row",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationAgeRow(@PrimaryKey(autoGenerate = false)
                            override val id: String = "",
                            val type: Int = 0,
                            var male: Int = 0,
                            var female: Int = 0,
                            var lgbt: Int = 0,
                            var pregnant: Int = 0,
                            var lactating: Int = 0,
                            var disabled: Int = 0,
                            var sick: Int = 0,
                            val evacuationId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is EvacuationAgeRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            male == other.male &&
            female == other.female &&
            lgbt == other.lgbt &&
            pregnant == other.pregnant &&
            lactating == other.lactating &&
            disabled == other.disabled &&
            sick == other.sick &&
            evacuationId == other.evacuationId)
            return true
        return false
    }
}