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
                              var isRice: Boolean = false,
                              var isCorn: Boolean = false,
                              var isVegetables: Boolean = false,
                              var isFruits: Boolean = false,
                              var isLivestock: Boolean = false,
                              var isFarmingOthers: Boolean = false,
                              var isBoat: Boolean = false,
                              var isFishingEquipment: Boolean = false,
                              var isAquacultures: Boolean = false,
                              var isFishingOthers: Boolean = false,
                              var isJeepney: Boolean = false,
                              var isTricycle: Boolean = false,
                              var isVan: Boolean = false,
                              var isTransportationOthers: Boolean = false,
                              var isVendor: Boolean = false,
                              var isSariSariStore: Boolean = false,
                              var isEntrepreneurshipOthers: Boolean = false,
                              var isEmployees: Boolean = false,
                              var isLaborers: Boolean = false,
                              var damageCost: Int = 0,
                              var remarks: String = "",
                              val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is EstimatedDamageRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            isRice == other.isRice &&
            isCorn == other.isCorn &&
            isVegetables == other.isVegetables &&
            isFruits == other.isFruits &&
            isLivestock == other.isLivestock &&
            isFarmingOthers == other.isFarmingOthers &&
            isBoat == other.isBoat &&
            isFishingEquipment == other.isFishingEquipment &&
            isAquacultures == other.isAquacultures &&
            isFishingOthers == other.isFishingOthers &&
            isJeepney == other.isJeepney &&
            isTricycle == other.isTricycle &&
            isVan == other.isVan &&
            isTransportationOthers == other.isTransportationOthers &&
            isVendor == other.isVendor &&
            isSariSariStore == other.isSariSariStore &&
            isEntrepreneurshipOthers == other.isEntrepreneurshipOthers &&
            isEmployees == other.isEmployees &&
            isLaborers == other.isLaborers &&
            damageCost == other.damageCost &&
            formId == other.formId &&
            formId == other.formId)
            return true
        return false
    }
}