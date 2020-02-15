package com.cpu.quikdata.data.evacuation.evacuationcoping

import com.cpu.quikdata.data.base.BaseCopyOnlyTest

import org.junit.Assert.*

class EvacuationCopingTest: BaseCopyOnlyTest<EvacuationCoping>() {

    companion object {
        private const val ID = "ID"
        private const val COPING_MECHANISM = "COPING_MECHANISM"
        private const val EVACUATION_ID = "EVACUATION_ID"
        private const val ID_COPY = "ID_COPY"
        private const val COPING_MECHANISM_COPY = "COPING_MECHANISM_COPY"
        private const val EVACUATION_ID_COPY = "EVACUATION_ID_COPY"
    }

    override fun initData(): EvacuationCoping {
        return EvacuationCoping(
            ID,
            COPING_MECHANISM,
            EVACUATION_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(COPING_MECHANISM, data.copingMechanism)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun copyFrom() {
        val copyData = EvacuationCoping(
            ID_COPY,
            COPING_MECHANISM_COPY,
            EVACUATION_ID_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData, data)
        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.copingMechanism, data.copingMechanism)
        assertNotEquals(copyData.evacuationId, data.evacuationId)
    }
}