package com.cpu.quikdata.data.casestories.casestoriesimage

import com.cpu.quikdata.data.base.BaseEqualityOnlyTest
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class CaseStoriesImageItemTest: BaseEqualityOnlyTest<CaseStoriesImageItem>() {

    companion object {
        private const val ID = "ID"
        private const val DATE_CREATED = 1000L
        private const val URI = "URI"
        private const val CASE_STORIES_ID = "CASE_STORIES_ID"
    }

    override fun initData(): CaseStoriesImageItem {
        return CaseStoriesImageItem(
            ID,
            DATE_CREATED,
            URI,
            CASE_STORIES_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(DATE_CREATED, data.dateCreated)
        assertEquals(URI, data.uri)
        assertEquals(CASE_STORIES_ID, data.caseStoriesId)
    }

    override fun equals() {
        val copy = CaseStoriesImageItem(
            ID,
            DATE_CREATED,
            URI,
            CASE_STORIES_ID
        )
        assertEquals(data, copy)
    }

    override fun notEquals() {
        assertNotEquals(data, PrefilledData())
        assertNotEquals(data, CaseStoriesImageItem())
    }
}