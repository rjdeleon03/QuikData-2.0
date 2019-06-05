package com.cpu.quikdata.data.evacuation

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "evacuation_item",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationItem(@PrimaryKey(autoGenerate = false)
                          val id: String = "",
                          var dateCreated: Long = 0L,
                          val formId: String = "") {

    fun copyFrom(evacuationItem: EvacuationItem) {
        dateCreated = evacuationItem.dateCreated
    }
}