package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "livelihoods_needs",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class LivelihoodsNeeds(@PrimaryKey(autoGenerate = false)
                            val id: String = "",
                            var assistanceFillGap: String = "",
                            var resourcesNeeded: String = "",
                            var familiesInAssistance: String = "",
                            var formIdRemote: String = "",
                            val formId: String = "") {

    fun copyFrom(livelihoodsNeeds: LivelihoodsNeeds) {
        assistanceFillGap = livelihoodsNeeds.assistanceFillGap
        resourcesNeeded = livelihoodsNeeds.resourcesNeeded
        familiesInAssistance = livelihoodsNeeds.familiesInAssistance
        formIdRemote = livelihoodsNeeds.formIdRemote
    }
}