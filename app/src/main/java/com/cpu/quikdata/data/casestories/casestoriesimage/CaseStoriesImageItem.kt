package com.cpu.quikdata.data.casestories.casestoriesimage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "case_stories_image_item",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CaseStoriesImageItem(@PrimaryKey(autoGenerate = false)
                                val id: String = "",
                                var dateCreated: Long = 0L,
                                var uri: String = "",
                                var formIdRemote: String = "",
                                val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CaseStoriesImageItem) return false
        if (this === other) return true
        if (id == other.id &&
            dateCreated == other.dateCreated &&
            uri == other.uri &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}