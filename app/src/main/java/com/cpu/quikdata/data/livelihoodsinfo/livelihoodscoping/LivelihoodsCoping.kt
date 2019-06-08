package com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "livelihoods_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class LivelihoodsCoping(@PrimaryKey(autoGenerate = false)
                             val id: String = "",
                             var copingStrategies: String = "",
                             var newIncome: String = "",
                             var livelihoodSkills: String = "",
                             var formIdRemote: String = "",
                             val formId: String = "") {

    fun copyFrom(livelihoodsCoping: LivelihoodsCoping) {
        copingStrategies = livelihoodsCoping.copingStrategies
        newIncome = livelihoodsCoping.newIncome
        livelihoodSkills = livelihoodsCoping.livelihoodSkills
        formIdRemote = livelihoodsCoping.formIdRemote
    }
}