package com.cpu.quikdata.data.casestories

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CaseStoriesTest {

    private lateinit var caseStories: CaseStories

    companion object {
        private const val ID = "ID"
        private const val TEXT = "TEXT"
        private const val FORM_ID = "FORM_ID"

        private const val ID_COPY = "ID_COPY"
        private const val TEXT_COPY = "TEXT_COPY"
        private const val FORM_ID_COPY = "FORM_ID_COPY"
    }

    @Before
    fun setUp() {
        caseStories = CaseStories(
            ID,
            TEXT,
            FORM_ID
        )
    }

    @Test
    fun getters() {
        assertEquals(ID, caseStories.id)
        assertEquals(TEXT, caseStories.text)
        assertEquals(FORM_ID, caseStories.formId)
    }

    @Test
    fun copyFrom() {
        val copyData = CaseStories(
            ID_COPY,
            TEXT_COPY,
            FORM_ID_COPY
        )
        caseStories.copyFrom(copyData)
        assertNotEquals(copyData.id, caseStories.id)
        assertEquals(copyData.text, caseStories.text)
        assertNotEquals(copyData.formId, caseStories.formId)
    }
}