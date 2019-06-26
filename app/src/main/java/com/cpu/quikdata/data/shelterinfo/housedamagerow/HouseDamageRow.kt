package com.cpu.quikdata.data.shelterinfo.housedamagerow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "house_damage_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class HouseDamageRow(@PrimaryKey(autoGenerate = false)
                          override val id: String = "",
                          val type: Int = 0,
                          var ownedHouseholds: Int = 0,
                          var rentedHouseholds: Int = 0,
                          var sharedHouseholds: Int = 0,
                          var ownedLand: Int = 0,
                          var rentedLand: Int = 0,
                          var tenantedLand: Int = 0,
                          var informalSettlers: Int = 0,
                          var partiallyDamaged: Int = 0,
                          var totallyDamaged: Int = 0,
                          val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is HouseDamageRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            ownedHouseholds == other.ownedHouseholds &&
            rentedHouseholds == other.rentedHouseholds &&
            sharedHouseholds == other.sharedHouseholds &&
            ownedLand == other.ownedLand &&
            rentedLand == other.rentedLand &&
            tenantedLand == other.tenantedLand &&
            informalSettlers == other.informalSettlers &&
            partiallyDamaged == other.partiallyDamaged &&
            totallyDamaged == other.totallyDamaged &&
            formId == other.formId)
            return true
        return false
    }
}