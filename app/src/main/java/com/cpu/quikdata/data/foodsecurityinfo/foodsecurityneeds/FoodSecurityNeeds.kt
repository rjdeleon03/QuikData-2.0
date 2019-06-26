package com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_needs",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityNeeds(@PrimaryKey(autoGenerate = false)
                             override val id: String = "",
                             var foodGapAssistance: String = "",
                             var familiesInNeed: String = "",
                             val formId: String = "") : BaseDataWithId {

    fun copyFrom(foodSecurityCoping: FoodSecurityNeeds) {
        foodGapAssistance = foodSecurityCoping.foodGapAssistance
        familiesInNeed = foodSecurityCoping.familiesInNeed
    }
}