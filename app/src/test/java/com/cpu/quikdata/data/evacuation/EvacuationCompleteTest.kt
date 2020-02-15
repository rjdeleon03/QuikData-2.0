package com.cpu.quikdata.data.evacuation

import com.cpu.quikdata.data.base.BaseDataTest
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo

import org.junit.Assert.*

class EvacuationCompleteTest : BaseDataTest<EvacuationComplete>() {

    companion object {
        private const val ID = "ID"
        private const val FORM_ID = "FORM_ID"
        private const val ID_1 = "ID_1"
        private const val EVACUATION_ID_1 = "EVACUATION_ID_1"
        private const val ID_2 = "ID_2"
        private const val EVACUATION_ID_2 = "EVACUATION_ID_2"
        private const val COUNT = 2
    }

    override fun initData(): EvacuationComplete {
        val root = EvacuationItem(id = ID, formId = FORM_ID)
        val siteInfo = arrayListOf(
            SiteInfo(id = ID_1, evacuationId = EVACUATION_ID_1),
            SiteInfo(id = ID_2, evacuationId = EVACUATION_ID_2))
        val breakdown = arrayListOf(
            EvacuationAgeRow(id = ID_1, evacuationId = EVACUATION_ID_1),
            EvacuationAgeRow(id = ID_2, evacuationId = EVACUATION_ID_2))
        val facilities = arrayListOf(
            EvacuationFacilities(id = ID_1, evacuationId = EVACUATION_ID_1),
            EvacuationFacilities(id = ID_2, evacuationId = EVACUATION_ID_2))
        val wash = arrayListOf(
            EvacuationWash(id = ID_1, evacuationId = EVACUATION_ID_1),
            EvacuationWash(id = ID_2, evacuationId = EVACUATION_ID_2))
        val protection = arrayListOf(
            EvacuationProtection(id = ID_1, evacuationId = EVACUATION_ID_1),
            EvacuationProtection(id = ID_2, evacuationId = EVACUATION_ID_2))
        val coping = arrayListOf(
            EvacuationCoping(id = ID_1, evacuationId = EVACUATION_ID_1),
            EvacuationCoping(id = ID_2, evacuationId = EVACUATION_ID_2))
        return EvacuationComplete().also {
            it.root = root
            it.siteInfo = siteInfo
            it.breakdown = breakdown
            it.facilities = facilities
            it.wash = wash
            it.protection = protection
            it.coping = coping
        }
    }

    override fun getters() {
        assertNotNull(data.root)
        data.root?.let {
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

        assertNotNull(data.breakdown)
        data.breakdown?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }

        assertNotNull(data.facilities)
        data.facilities?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }

        assertNotNull(data.wash)
        data.wash?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }

        assertNotNull(data.protection)
        data.protection?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }

        assertNotNull(data.coping)
        data.coping?.let {
            assertEquals(it.size, COUNT)
            assertEquals(it[0].id, ID_1)
            assertEquals(it[0].evacuationId, EVACUATION_ID_1)
            assertEquals(it[1].id, ID_2)
            assertEquals(it[1].evacuationId, EVACUATION_ID_2)
        }
    }

}