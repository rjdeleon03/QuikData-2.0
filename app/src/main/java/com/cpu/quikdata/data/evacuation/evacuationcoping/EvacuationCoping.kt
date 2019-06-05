package com.cpu.quikdata.data.evacuation.evacuationcoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "evacuation_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationCoping(@PrimaryKey(autoGenerate = false)
                            val id: String = "",
                            var copingMechanism: String = "",
                            val formId: String = "") {

    fun copyFrom(evacuationCoping: EvacuationCoping) {
        copingMechanism = evacuationCoping.copingMechanism
    }
}