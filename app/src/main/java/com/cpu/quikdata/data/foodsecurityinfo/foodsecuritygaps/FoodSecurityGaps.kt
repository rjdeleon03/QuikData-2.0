package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityGaps(@PrimaryKey(autoGenerate = false)
                            override val id: String = "",
                            var assistanceAppropriate: String = "",
                            var assistanceEnough: String = "",
                            var assistanceEqualAccess: String = "",
                            var specificNeedsMet: String = "",
                            val formId: String = "") : BaseDataWithId {

    fun copyFrom(foodSecurityGaps: FoodSecurityGaps) {
        assistanceAppropriate = foodSecurityGaps.assistanceAppropriate
        assistanceEnough = foodSecurityGaps.assistanceEnough
        assistanceEqualAccess = foodSecurityGaps.assistanceEqualAccess
        specificNeedsMet = foodSecurityGaps.specificNeedsMet
    }
}