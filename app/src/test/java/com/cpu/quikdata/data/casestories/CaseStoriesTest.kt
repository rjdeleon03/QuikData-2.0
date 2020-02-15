package com.cpu.quikdata.data.casestories

import com.cpu.quikdata.data.base.BaseCopyOnlyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CaseStoriesTest: BaseCopyOnlyTest<CaseStories>() {

    companion object {
        private const val ID = "ID"
        private const val TEXT = "TEXT"
        private const val FORM_ID = "FORM_ID"

        private const val ID_COPY = "ID_COPY"
        private const val TEXT_COPY = "TEXT_COPY"
        private const val FORM_ID_COPY = "FORM_ID_COPY"
    }

    override fun initData(): CaseStories {
        return CaseStories(
            ID,
            TEXT,
            FORM_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(TEXT, data.text)
        assertEquals(FORM_ID, data.formId)
    }

    override fun copyFrom() {
        val copyData = CaseStories(
            ID_COPY,
            TEXT_COPY,
            FORM_ID_COPY
        )
        data.copyFrom(copyData)
        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.text, data.text)
        assertNotEquals(copyData.formId, data.formId)
    }
}