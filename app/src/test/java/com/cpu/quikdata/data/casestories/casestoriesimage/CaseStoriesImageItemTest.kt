package com.cpu.quikdata.data.casestories.casestoriesimage

import com.cpu.quikdata.data.prefilleddata.PrefilledData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CaseStoriesImageItemTest {

    private lateinit var caseStoriesImageItem: CaseStoriesImageItem

    companion object {
        private const val ID = "ID"
        private const val DATE_CREATED = 1000L
        private const val URI = "URI"
        private const val CASE_STORIES_ID = "CASE_STORIES_ID"
    }

    @Before
    fun setUp() {
        caseStoriesImageItem = CaseStoriesImageItem(
            ID,
            DATE_CREATED,
            URI,
            CASE_STORIES_ID
        )
    }

    @Test
    fun getters() {
        assertEquals(ID, caseStoriesImageItem.id)
        assertEquals(DATE_CREATED, caseStoriesImageItem.dateCreated)
        assertEquals(URI, caseStoriesImageItem.uri)
        assertEquals(CASE_STORIES_ID, caseStoriesImageItem.caseStoriesId)
    }

    @Test
    fun equals() {
        val copy = CaseStoriesImageItem(
            ID,
            DATE_CREATED,
            URI,
            CASE_STORIES_ID
        )
        assertEquals(caseStoriesImageItem, copy)
    }

    @Test
    fun notEquals() {
        assertNotEquals(caseStoriesImageItem, PrefilledData())
        assertNotEquals(caseStoriesImageItem, CaseStoriesImageItem())
    }
}