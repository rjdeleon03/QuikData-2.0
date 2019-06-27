package com.cpu.quikdata.data.evacuation.evacuationprotection

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.evacuation.EvacuationItem

@Entity(tableName = "evacuation_protection",
    indices = [Index("evacuationId")],
    foreignKeys = [ForeignKey(entity = EvacuationItem::class,
        parentColumns = ["id"],
        childColumns = ["evacuationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EvacuationProtection(@PrimaryKey(autoGenerate = false)
                                override val id: String = "",
                                var unaccompaniedChildren: Boolean = true,
                                var unaccompaniedChildrenRemarks: String = "",
                                var toiletLocks: Boolean = true,
                                var toiletLocksRemarks: String = "",
                                var segregatedToilet: Boolean = true,
                                var segregatedToiletRemarks: String = "",
                                var properLighting: Boolean = true,
                                var properLightingRemarks: String = "",
                                var securityOfficers: Boolean = true,
                                var securityOfficersRemarks: String = "",
                                var sleepingPartitions: Boolean = true,
                                var sleepingPartitionsRemarks: String = "",
                                var childFriendly: Boolean = true,
                                var childFriendlyRemarks: String = "",
                                var pregnantSpace: Boolean = true,
                                var pregnantSpaceRemarks: String = "",
                                var disabledSpace: Boolean = true,
                                var disabledSpaceRemarks: String = "",
                                var religiousSpace: Boolean = true,
                                var religiousSpaceRemarks: String = "",
                                var gbvReferral: Boolean = true,
                                var gbvReferralRemarks: String = "",
                                var gbvProtection: Boolean = true,
                                var gbvProtectionRemarks: String = "",
                                var gbvFocal: Boolean = true,
                                var gbvFocalRemarks: String = "",
                                val evacuationId: String = "") : BaseDataWithId {

    fun copyFrom(evacuationProtection: EvacuationProtection) {
        unaccompaniedChildren = evacuationProtection.unaccompaniedChildren
        unaccompaniedChildrenRemarks = evacuationProtection.unaccompaniedChildrenRemarks
        toiletLocks = evacuationProtection.toiletLocks
        toiletLocksRemarks = evacuationProtection.toiletLocksRemarks
        segregatedToilet = evacuationProtection.segregatedToilet
        segregatedToiletRemarks = evacuationProtection.segregatedToiletRemarks
        properLighting = evacuationProtection.properLighting
        properLightingRemarks = evacuationProtection.properLightingRemarks
        securityOfficers = evacuationProtection.securityOfficers
        securityOfficersRemarks = evacuationProtection.securityOfficersRemarks
        sleepingPartitions = evacuationProtection.sleepingPartitions
        sleepingPartitionsRemarks = evacuationProtection.sleepingPartitionsRemarks
        childFriendly = evacuationProtection.childFriendly
        childFriendlyRemarks = evacuationProtection.childFriendlyRemarks
        pregnantSpace = evacuationProtection.pregnantSpace
        pregnantSpaceRemarks = evacuationProtection.pregnantSpaceRemarks
        disabledSpace = evacuationProtection.disabledSpace
        disabledSpaceRemarks = evacuationProtection.disabledSpaceRemarks
        religiousSpace = evacuationProtection.religiousSpace
        religiousSpaceRemarks = evacuationProtection.religiousSpaceRemarks
        gbvReferral = evacuationProtection.gbvReferral
        gbvReferralRemarks = evacuationProtection.gbvReferralRemarks
        gbvProtection = evacuationProtection.gbvProtection
        gbvProtectionRemarks = evacuationProtection.gbvProtectionRemarks
        gbvFocal = evacuationProtection.gbvFocal
        gbvFocalRemarks = evacuationProtection.gbvFocalRemarks
    }
}