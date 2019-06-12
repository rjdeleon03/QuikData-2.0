package com.cpu.quikdata.data.casestories

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem

class CaseStoriesComplete {

    @Embedded
    var root: CaseStories? = null

    @Relation(parentColumn = "id",
        entityColumn = "caseStoriesId",
        entity = CaseStoriesImageItem::class)
    var images: List<CaseStoriesImageItem>? = null
}