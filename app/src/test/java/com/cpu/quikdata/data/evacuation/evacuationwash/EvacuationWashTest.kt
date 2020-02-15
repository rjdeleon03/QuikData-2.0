package com.cpu.quikdata.data.evacuation.evacuationwash

import com.cpu.quikdata.data.base.BaseCopyOnlyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class EvacuationWashTest : BaseCopyOnlyTest<EvacuationWash>() {

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

    override fun initData(): EvacuationWash {
        return EvacuationWash(
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
            EVACUATION_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(FLAG, data.foodPreparation)
        assertEquals(REMARKS, data.foodPreparationRemarks)
        assertEquals(FLAG, data.waterSource)
        assertEquals(REMARKS, data.waterSourceRemarks)
        assertEquals(FLAG, data.toiletBaths)
        assertEquals(REMARKS, data.toiletBathsRemarks)
        assertEquals(FLAG, data.garbageDisposal)
        assertEquals(REMARKS, data.garbageDisposalRemarks)
        assertEquals(FLAG, data.clinic)
        assertEquals(REMARKS, data.clinicRemarks)
        assertEquals(FLAG, data.sick)
        assertEquals(REMARKS, data.sickRemarks)
        assertEquals(FLAG, data.handWashing)
        assertEquals(REMARKS, data.handWashingRemarks)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun copyFrom() {
        val copyData = EvacuationWash(
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
            EVACUATION_ID_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.foodPreparation, data.foodPreparation)
        assertEquals(copyData.foodPreparationRemarks, data.foodPreparationRemarks)
        assertEquals(copyData.waterSource, data.waterSource)
        assertEquals(copyData.waterSourceRemarks, data.waterSourceRemarks)
        assertEquals(copyData.toiletBaths, data.toiletBaths)
        assertEquals(copyData.toiletBathsRemarks, data.toiletBathsRemarks)
        assertEquals(copyData.garbageDisposal, data.garbageDisposal)
        assertEquals(copyData.garbageDisposalRemarks, data.garbageDisposalRemarks)
        assertEquals(copyData.clinic, data.clinic)
        assertEquals(copyData.clinicRemarks, data.clinicRemarks)
        assertEquals(copyData.sick, data.sick)
        assertEquals(copyData.sickRemarks, data.sickRemarks)
        assertEquals(copyData.handWashing, data.handWashing)
        assertEquals(copyData.handWashingRemarks, data.handWashingRemarks)
        assertNotEquals(copyData.evacuationId, data.evacuationId)
    }

}