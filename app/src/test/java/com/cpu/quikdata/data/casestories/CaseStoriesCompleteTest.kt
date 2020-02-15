package com.cpu.quikdata.data.casestories

import com.cpu.quikdata.data.base.BaseDataTest
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class CaseStoriesCompleteTest: BaseDataTest<CaseStoriesComplete>() {

    companion object {
        private const val ID = "ID"
        private const val TEXT = "TEXT"
        private const val FORM_ID = "FORM_ID"
        private const val DATE_CREATED = 1000L
        private const val URI = "URI"
        private const val IMAGE_ID_1 = "IMAGE_ID_1"
        private const val IMAGE_ID_2 = "IMAGE_ID_2"
        private const val IMAGE_COUNT = 2
    }

    override fun initData(): CaseStoriesComplete {

        val caseStories = CaseStories(ID, TEXT, FORM_ID)
        val imageList = arrayListOf(
            CaseStoriesImageItem(IMAGE_ID_1, DATE_CREATED, URI, ID),
            CaseStoriesImageItem(IMAGE_ID_2, DATE_CREATED, URI, ID))
        return CaseStoriesComplete().apply {
            root = caseStories
            images = imageList
        }
    }

    override fun getters() {
        assertNotNull(data.root)
        data.root?.let {
            assertEquals(it.id, ID)
            assertEquals(it.text, TEXT)
            assertEquals(it.formId, FORM_ID)
        }
        assertNotNull(data.images)
        assertEquals(data.images!!.size, IMAGE_COUNT)
        data.images?.let {
            assertEquals(it[0].id, IMAGE_ID_1)
            assertEquals(it[0].uri, URI)
            assertEquals(it[0].caseStoriesId, ID)
            assertEquals(it[1].id, IMAGE_ID_2)
            assertEquals(it[1].uri, URI)
            assertEquals(it[1].caseStoriesId, ID)
        }
    }
}