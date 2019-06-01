package com.cpu.quikdata.data.health.healthgaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "health_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class HealthGaps(@PrimaryKey(autoGenerate = false)
                      val id: String = "",
                      var nearestHospital: String = "",
                      var servicesAvailable: String = "",
                      var servicesAccessible: String = "",
                      var reproductiveHealth: String = "",
                      val formId: String = "") {

    fun copyFrom(healthGaps: HealthGaps) {
        nearestHospital = healthGaps.nearestHospital
        servicesAvailable = healthGaps.servicesAvailable
        servicesAccessible = healthGaps.servicesAccessible
        reproductiveHealth = healthGaps.reproductiveHealth
    }
}