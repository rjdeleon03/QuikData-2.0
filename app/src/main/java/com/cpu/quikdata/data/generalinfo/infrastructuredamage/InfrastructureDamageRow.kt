package com.cpu.quikdata.data.generalinfo.infrastructuredamage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "infrastructure_damage_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class InfrastructureDamageRow(@PrimaryKey(autoGenerate = false)
                                   override val id: String = "",
                                   val type: Int = 0,
                                   var numberOfInfrastructure: Int = 0,
                                   var isFunctional: Boolean = true,
                                   var remarks: String = "",
                                   val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is InfrastructureDamageRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            numberOfInfrastructure == other.numberOfInfrastructure &&
            isFunctional == other.isFunctional &&
            remarks == other.remarks &&
            formId == other.formId)
            return true
        return false
    }
}