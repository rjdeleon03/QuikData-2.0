package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "livelihoods_needs",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class LivelihoodsNeeds(@PrimaryKey(autoGenerate = false)
                            override val id: String = "",
                            var assistanceFillGap: String = "",
                            var resourcesNeeded: String = "",
                            var familiesInAssistance: String = "",
                            val formId: String = "") : BaseDataWithId {

    fun copyFrom(livelihoodsNeeds: LivelihoodsNeeds) {
        assistanceFillGap = livelihoodsNeeds.assistanceFillGap
        resourcesNeeded = livelihoodsNeeds.resourcesNeeded
        familiesInAssistance = livelihoodsNeeds.familiesInAssistance
    }
}