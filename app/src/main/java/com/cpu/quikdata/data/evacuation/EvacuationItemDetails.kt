package com.cpu.quikdata.data.evacuation

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo

class EvacuationItemDetails {

    @Embedded
    var item: EvacuationItem? = null

    @Relation(parentColumn = "id", entityColumn = "evacuationId", entity = SiteInfo::class)
    var siteInfo: List<SiteInfo>? = null
}