package com.cpu.quikdata.data.watersanitationinfo.washcoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "wash_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class WashCoping(@PrimaryKey(autoGenerate = false)
                      override val id: String = "",
                      var copingStrategies: String = "",
                      val formId: String = "") : BaseDataWithId {

    fun copyFrom(washCoping: WashCoping) {
        copingStrategies = washCoping.copingStrategies
    }
}