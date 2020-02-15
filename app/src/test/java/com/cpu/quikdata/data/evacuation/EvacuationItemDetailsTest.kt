package com.cpu.quikdata.data.evacuation

import com.cpu.quikdata.data.base.BaseDataTest
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo

import org.junit.Assert.*

class EvacuationItemDetailsTest : BaseDataTest<EvacuationItemDetails>() {

    companion object {
        private const val ID = "ID"
        private const val FORM_ID = "FORM_ID"
        private const val ID_1 = "ID_1"
        private const val EVACUATION_ID_1 = "EVACUATION_ID_1"
        private const val ID_2 = "ID_2"
        private const val EVACUATION_ID_2 = "EVACUATION_ID_2"
        private const val COUNT = 2
    }

    override fun initData(): EvacuationItemDetails {
        val item = EvacuationItem(id = ID, formId = FORM_ID)
        val siteInfo = arrayListOf(
            SiteInfo(id = ID_1, evacuationId = EVACUATION_ID_1),
            SiteInfo(id = ID_2, evacuationId = EVACUATION_ID_2)
        )
        return EvacuationItemDetails().also {
            it.item = item
            it.siteInfo = siteInfo
        }
    }

    override fun getters() {
        assertNotNull(data.item)
        data.item?.let {
            assertEquals(it.id, ID)
            assertEquals(it.formId, FORM_ID)
        }

        assertNotNull(data.siteInfo)
        data.siteInfo?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }
    }
}