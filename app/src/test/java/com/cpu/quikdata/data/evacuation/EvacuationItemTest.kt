package com.cpu.quikdata.data.evacuation

import com.cpu.quikdata.data.base.BaseCopyOnlyTest

import org.junit.Assert.*
import java.net.IDN

class EvacuationItemTest : BaseCopyOnlyTest<EvacuationItem>() {

    companion object {
        private const val ID = "ID"
        private const val DATE_CREATED = 1000L
        private const val FORM_ID = "FORM_ID"
        private const val ID_COPY = "ID_COPY"
        private const val DATE_CREATED_COPY = 100000L
        private const val FORM_ID_COPY = "FORM_ID_COPY"
    }

    override fun initData(): EvacuationItem {
        return EvacuationItem(ID, DATE_CREATED, FORM_ID)
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(DATE_CREATED, data.dateCreated)
        assertEquals(FORM_ID, data.formId)
    }

    override fun copyFrom() {
        val copyData = EvacuationItem(
            ID_COPY, DATE_CREATED_COPY, FORM_ID_COPY)
        data.copyFrom(copyData)

        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.dateCreated, data.dateCreated)
        assertNotEquals(copyData.formId, data.formId)
    }

}