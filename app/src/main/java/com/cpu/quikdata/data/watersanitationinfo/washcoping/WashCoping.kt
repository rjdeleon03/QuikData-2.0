package com.cpu.quikdata.data.watersanitationinfo.washcoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "wash_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class WashCoping(@PrimaryKey(autoGenerate = false)
                      val id: String = "",
                      var copingStrategies: String = "",
                      var formIdRemote: String = "",
                      val formId: String = "") {

    fun copyFrom(washCoping: WashCoping) {
        copingStrategies = washCoping.copingStrategies
        formIdRemote = washCoping.formIdRemote
    }
}