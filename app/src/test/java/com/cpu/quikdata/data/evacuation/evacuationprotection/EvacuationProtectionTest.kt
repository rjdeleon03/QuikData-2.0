package com.cpu.quikdata.data.evacuation.evacuationprotection

import com.cpu.quikdata.data.base.BaseCopyOnlyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class EvacuationProtectionTest : BaseCopyOnlyTest<EvacuationProtection>() {

    companion object {
        private const val ID = "ID"
        private const val FLAG = false
        private const val REMARKS = "REMARKS"
        private const val EVACUATION_ID = "EVACUATION_ID"
        private const val ID_COPY = "ID_COPY"
        private const val FLAG_COPY = true
        private const val REMARKS_COPY = "REMARKS_COPY"
        private const val EVACUATION_ID_COPY = "EVACUATION_ID_COPY"
    }

    override fun initData(): EvacuationProtection {
        return EvacuationProtection(
            ID,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            FLAG,
            REMARKS,
            EVACUATION_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(FLAG, data.unaccompaniedChildren)
        assertEquals(REMARKS, data.unaccompaniedChildrenRemarks)
        assertEquals(FLAG, data.toiletLocks)
        assertEquals(REMARKS, data.toiletLocksRemarks)
        assertEquals(FLAG, data.segregatedToilet)
        assertEquals(REMARKS, data.segregatedToiletRemarks)
        assertEquals(FLAG, data.properLighting)
        assertEquals(REMARKS, data.properLightingRemarks)
        assertEquals(FLAG, data.securityOfficers)
        assertEquals(REMARKS, data.securityOfficersRemarks)
        assertEquals(FLAG, data.sleepingPartitions)
        assertEquals(REMARKS, data.sleepingPartitionsRemarks)
        assertEquals(FLAG, data.childFriendly)
        assertEquals(REMARKS, data.childFriendlyRemarks)
        assertEquals(FLAG, data.pregnantSpace)
        assertEquals(REMARKS, data.pregnantSpaceRemarks)
        assertEquals(FLAG, data.disabledSpace)
        assertEquals(REMARKS, data.disabledSpaceRemarks)
        assertEquals(FLAG, data.religiousSpace)
        assertEquals(REMARKS, data.religiousSpaceRemarks)
        assertEquals(FLAG, data.gbvReferral)
        assertEquals(REMARKS, data.gbvReferralRemarks)
        assertEquals(FLAG, data.gbvProtection)
        assertEquals(REMARKS, data.gbvProtectionRemarks)
        assertEquals(FLAG, data.gbvFocal)
        assertEquals(REMARKS, data.gbvFocalRemarks)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun copyFrom() {
        val copyData = EvacuationProtection(
            ID_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            FLAG_COPY,
            REMARKS_COPY,
            EVACUATION_ID_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.unaccompaniedChildren, data.unaccompaniedChildren)
        assertEquals(copyData.unaccompaniedChildrenRemarks, data.unaccompaniedChildrenRemarks)
        assertEquals(copyData.toiletLocks, data.toiletLocks)
        assertEquals(copyData.toiletLocksRemarks, data.toiletLocksRemarks)
        assertEquals(copyData.segregatedToilet, data.segregatedToilet)
        assertEquals(copyData.segregatedToiletRemarks, data.segregatedToiletRemarks)
        assertEquals(copyData.properLighting, data.properLighting)
        assertEquals(copyData.properLightingRemarks, data.properLightingRemarks)
        assertEquals(copyData.securityOfficers, data.securityOfficers)
        assertEquals(copyData.securityOfficersRemarks, data.securityOfficersRemarks)
        assertEquals(copyData.sleepingPartitions, data.sleepingPartitions)
        assertEquals(copyData.sleepingPartitionsRemarks, data.sleepingPartitionsRemarks)
        assertEquals(copyData.childFriendly, data.childFriendly)
        assertEquals(copyData.childFriendlyRemarks, data.childFriendlyRemarks)
        assertEquals(copyData.pregnantSpace, data.pregnantSpace)
        assertEquals(copyData.pregnantSpaceRemarks, data.pregnantSpaceRemarks)
        assertEquals(copyData.disabledSpace, data.disabledSpace)
        assertEquals(copyData.disabledSpaceRemarks, data.disabledSpaceRemarks)
        assertEquals(copyData.religiousSpace, data.religiousSpace)
        assertEquals(copyData.religiousSpaceRemarks, data.religiousSpaceRemarks)
        assertEquals(copyData.gbvReferral, data.gbvReferral)
        assertEquals(copyData.gbvReferralRemarks, data.gbvReferralRemarks)
        assertEquals(copyData.gbvProtection, data.gbvProtection)
        assertEquals(copyData.gbvProtectionRemarks, data.gbvProtectionRemarks)
        assertEquals(copyData.gbvFocal, data.gbvFocal)
        assertEquals(copyData.gbvFocalRemarks, data.gbvFocalRemarks)
        assertNotEquals(copyData.evacuationId, data.evacuationId)
    }

}