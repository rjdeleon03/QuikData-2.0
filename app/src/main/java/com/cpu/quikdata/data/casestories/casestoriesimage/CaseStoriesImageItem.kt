package com.cpu.quikdata.data.casestories.casestoriesimage

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.casestories.CaseStories

@Entity(tableName = "case_stories_image_item",
    indices = [Index("caseStoriesId")],
    foreignKeys = [ForeignKey(entity = CaseStories::class,
        parentColumns = ["id"],
        childColumns = ["caseStoriesId"],
        onDelete = ForeignKey.CASCADE
    )])
data class CaseStoriesImageItem(@PrimaryKey(autoGenerate = false)
                                val id: String = "",
                                var dateCreated: Long = 0L,
                                var uri: String = "",
                                val caseStoriesId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CaseStoriesImageItem) return false
        if (this === other) return true
        if (id == other.id &&
            dateCreated == other.dateCreated &&
            uri == other.uri &&
            caseStoriesId == other.caseStoriesId)
            return true
        return false
    }
}