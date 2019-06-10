package com.cpu.quikdata.data.health.healthcoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "health_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class HealthCoping(@PrimaryKey(autoGenerate = false)
                        val id: String = "",
                        var copingStrategies: String = "",
                        var formIdRemote: String = "",
                        val formId: String = "") {

    fun copyFrom(healthCoping: HealthCoping) {
        copingStrategies = healthCoping.copingStrategies
        formIdRemote = healthCoping.formIdRemote
    }
}