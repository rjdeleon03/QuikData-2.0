package com.cpu.quikdata.data.prefilleddata

import com.cpu.quikdata.data.base.BaseDataTest
import org.junit.Assert.assertEquals

class PrefilledDataTest: BaseDataTest<PrefilledData>() {

    companion object {
        private const val ID = "ID"
        private const val ORGANIZATION = "ORGANIZATION"
        private const val SITIO = "SITIO"
        private const val BARANGAY = "BARANGAY"
        private const val CITY = "CITY"
        private const val PROVINCE = "PROVINCE"
        private const val COUNT = 0
    }

    override fun initData(): PrefilledData {
        return PrefilledData(
            ID,
            ORGANIZATION,
            SITIO,
            BARANGAY,
            CITY,
            PROVINCE,
            COUNT,
            COUNT + 1,
            COUNT + 2,
            COUNT + 3,
            COUNT + 4,
            COUNT + 5,
            COUNT + 6,
            COUNT + 7,
            COUNT + 8,
            COUNT + 9,
            COUNT + 10,
            COUNT + 11,
            COUNT,
            COUNT,
            COUNT,
            COUNT,
            COUNT
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(ORGANIZATION, data.organization)
        assertEquals(SITIO, data.sitio)
        assertEquals(BARANGAY, data.barangay)
        assertEquals(CITY, data.city)
        assertEquals(PROVINCE, data.province)
        assertEquals(COUNT, data.male_0to5)
        assertEquals(COUNT + 1, data.female_0to5)
        assertEquals(COUNT + 2, data.male_6to9)
        assertEquals(COUNT + 3, data.female_6to9)
        assertEquals(COUNT + 4, data.male_10to12)
        assertEquals(COUNT + 5, data.female_10to12)
        assertEquals(COUNT + 6, data.male_13to17)
        assertEquals(COUNT + 7, data.female_13to17)
        assertEquals(COUNT + 8, data.male_18to59)
        assertEquals(COUNT + 9, data.female_18to59)
        assertEquals(COUNT + 10, data.male_60plus)
        assertEquals(COUNT + 11, data.female_60plus)
        assertEquals(COUNT, data.totalFamilies)
        assertEquals(COUNT, data.totalHouseholds)
        assertEquals(COUNT, data.shelterConcrete)
        assertEquals(COUNT, data.shelterSemiConcrete)
        assertEquals(COUNT, data.shelterLightMaterials)
    }
}