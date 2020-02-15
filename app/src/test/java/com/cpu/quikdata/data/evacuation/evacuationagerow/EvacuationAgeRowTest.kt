package com.cpu.quikdata.data.evacuation.evacuationagerow

import com.cpu.quikdata.data.base.BaseEqualityOnlyTest
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class EvacuationAgeRowTest: BaseEqualityOnlyTest<EvacuationAgeRow>() {

    companion object {
        private const val ID = "ID"
        private const val TYPE = 0
        private const val COUNT = 0
        private const val EVACUATION_ID = "EVACUATION_ID"
    }

    override fun initData(): EvacuationAgeRow {
        return EvacuationAgeRow(
            ID,
            TYPE,
            COUNT,
            COUNT + 1,
            COUNT + 2,
            COUNT + 3,
            COUNT + 4,
            COUNT + 5,
            COUNT + 6,
            EVACUATION_ID
        )
    }
    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(TYPE, data.type)
        assertEquals(COUNT, data.male)
        assertEquals(COUNT + 1, data.female)
        assertEquals(COUNT + 2, data.lgbt)
        assertEquals(COUNT + 3, data.pregnant)
        assertEquals(COUNT + 4, data.lactating)
        assertEquals(COUNT + 5, data.disabled)
        assertEquals(COUNT + 6, data.sick)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun equals() {
        val copy = EvacuationAgeRow(
            ID,
            TYPE,
            COUNT,
            COUNT + 1,
            COUNT + 2,
            COUNT + 3,
            COUNT + 4,
            COUNT + 5,
            COUNT + 6,
            EVACUATION_ID
        )
        assertEquals(data, copy)
    }

    override fun notEquals() {
        assertNotEquals(data, PrefilledData())
        assertNotEquals(data, EvacuationAgeRow())
    }
}