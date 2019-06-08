package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityCoping(@PrimaryKey(autoGenerate = false)
                              val id: String = "",
                              var copingStrategies: String = "",
                              var formIdRemote: String = "",
                              val formId: String = "") {

    fun copyFrom(foodSecurityCoping: FoodSecurityCoping) {
        copingStrategies = foodSecurityCoping.copingStrategies
        formIdRemote = foodSecurityCoping.formIdRemote
    }
}