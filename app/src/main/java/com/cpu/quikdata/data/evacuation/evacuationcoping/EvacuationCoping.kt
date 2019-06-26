package com.cpu.quikdata.data.evacuation.evacuationcoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_coping",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationCoping(@PrimaryKey(autoGenerate = false)
                            override val id: String = "",
                            var copingMechanism: String = "",
                            val evacuationId: String = "") : BaseDataWithId {

    fun copyFrom(evacuationCoping: EvacuationCoping) {
        copingMechanism = evacuationCoping.copingMechanism
    }
}