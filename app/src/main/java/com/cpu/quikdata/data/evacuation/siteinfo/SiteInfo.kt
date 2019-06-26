package com.cpu.quikdata.data.evacuation.siteinfo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "site_info",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class SiteInfo(@PrimaryKey(autoGenerate = false)
                    override val id: String = "",
                    var name: String = "",
                    var location: String = "",
                    var haveMoved: Boolean = true,
                    var type: Int = 0,
                    var isLguDesignated: Boolean = true,
                    var distanceFromCommunity: String = "",
                    var evacuationDate: Long = 0L,
                    var shelterSize: String = "",
                    var householdsAndFamilies: Int = 0,
                    val evacuationId: String = "") : BaseDataWithId {

    fun copyFrom(siteInfo: SiteInfo) {
        name = siteInfo.name
        location = siteInfo.location
        haveMoved = siteInfo.haveMoved
        type = siteInfo.type
        isLguDesignated = siteInfo.isLguDesignated
        distanceFromCommunity = siteInfo.distanceFromCommunity
        evacuationDate = siteInfo.evacuationDate
        shelterSize = siteInfo.shelterSize
        householdsAndFamilies = siteInfo.householdsAndFamilies
    }
}