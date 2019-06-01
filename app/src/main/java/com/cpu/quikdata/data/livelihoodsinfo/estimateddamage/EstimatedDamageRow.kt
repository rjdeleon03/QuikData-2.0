package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "estimated_damage_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EstimatedDamageRow(@PrimaryKey(autoGenerate = false)
                              val id: String = "",
                              var type: Int = 0,
                              var damageCost: Int = 0,
                              var remarks: String = "",
                              val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is EstimatedDamageRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            damageCost == other.damageCost &&
            formId == other.formId &&
            formId == other.formId)
            return true
        return false
    }
}