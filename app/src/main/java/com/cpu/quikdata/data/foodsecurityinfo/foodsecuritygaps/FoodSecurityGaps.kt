package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityGaps(@PrimaryKey(autoGenerate = false)
                            val id: String = "",
                            var assistanceAppropriate: String = "",
                            var assistanceEnough: String = "",
                            var assistanceEqualAccess: String = "",
                            var specificNeedsMet: String = "",
                            var formIdRemote: String = "",
                            val formId: String = "") {

    fun copyFrom(foodSecurityGaps: FoodSecurityGaps) {
        assistanceAppropriate = foodSecurityGaps.assistanceAppropriate
        assistanceEnough = foodSecurityGaps.assistanceEnough
        assistanceEqualAccess = foodSecurityGaps.assistanceEqualAccess
        specificNeedsMet = foodSecurityGaps.specificNeedsMet
        formIdRemote = foodSecurityGaps.formIdRemote
    }
}