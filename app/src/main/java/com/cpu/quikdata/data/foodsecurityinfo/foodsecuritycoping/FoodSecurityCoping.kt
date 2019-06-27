package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityCoping(@PrimaryKey(autoGenerate = false)
                              override val id: String = "",
                              var copingStrategies: String = "",
                              val formId: String = "") : BaseDataWithId {

    fun copyFrom(foodSecurityCoping: FoodSecurityCoping) {
        copingStrategies = foodSecurityCoping.copingStrategies
    }
}