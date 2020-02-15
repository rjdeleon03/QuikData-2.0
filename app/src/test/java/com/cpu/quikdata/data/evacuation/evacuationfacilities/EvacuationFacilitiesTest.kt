package com.cpu.quikdata.data.evacuation.evacuationfacilities

import com.cpu.quikdata.data.base.BaseCopyOnlyTest

import org.junit.Assert.*

class EvacuationFacilitiesTest : BaseCopyOnlyTest<EvacuationFacilities>() {

    companion object {
        private const val ID = "ID"
        private const val CAPACITY = 0
        private const val FLAG = false
        private const val REMARKS = "REMARKS"
        private const val EVACUATION_ID = "EVACUATION_ID"
        private const val ID_COPY = "ID_COPY"
        private const val CAPACITY_COPY = 100
        private const val FLAG_COPY = true
        private const val REMARKS_COPY = "REMARKS"
        private const val EVACUATION_ID_COPY = "EVACUATION_ID_COPY"
    }

    override fun initData(): EvacuationFacilities {
        return EvacuationFacilities(
            ID,
            CAPACITY,
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
        assertEquals(CAPACITY, data.capacity)
        assertEquals(FLAG, data.toilet)
        assertEquals(REMARKS, data.toiletRemarks)
        assertEquals(FLAG, data.wideEntrance)
        assertEquals(REMARKS, data.wideEntranceRemarks)
        assertEquals(FLAG, data.electricity)
        assertEquals(REMARKS, data.electricityRemarks)
        assertEquals(FLAG, data.waterSupply)
        assertEquals(REMARKS, data.waterSupplyRemarks)
        assertEquals(FLAG, data.ventilation)
        assertEquals(REMARKS, data.ventilationRemarks)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun copyFrom() {
        val copyData = EvacuationFacilities(
            ID_COPY,
            CAPACITY_COPY,
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

        assertNotEquals(copyData, data)
        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.capacity, data.capacity)
        assertEquals(copyData.toilet, data.toilet)
        assertEquals(copyData.toiletRemarks, data.toiletRemarks)
        assertEquals(copyData.wideEntrance, data.wideEntrance)
        assertEquals(copyData.wideEntranceRemarks, data.wideEntranceRemarks)
        assertEquals(copyData.electricity, data.electricity)
        assertEquals(copyData.electricityRemarks, data.electricityRemarks)
        assertEquals(copyData.waterSupply, data.waterSupply)
        assertEquals(copyData.waterSupplyRemarks, data.waterSupplyRemarks)
        assertEquals(copyData.ventilation, data.ventilation)
        assertEquals(copyData.ventilationRemarks, data.ventilationRemarks)
        assertNotEquals(copyData.evacuationId, data.evacuationId)
    }

}