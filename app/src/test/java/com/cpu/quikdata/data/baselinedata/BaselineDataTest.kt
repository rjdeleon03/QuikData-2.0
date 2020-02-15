package com.cpu.quikdata.data.baselinedata

import com.cpu.quikdata.data.base.BaseCopyOnlyTest
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import org.junit.Test

import org.junit.Assert.*

class BaselineDataTest: BaseCopyOnlyTest<BaselineData>() {

    companion object {
        private const val ID = "ID"
        private const val PREFILLED = true
        private const val ORGANIZATION = "ORGANIZATION"
        private const val SITIO = "SITIO"
        private const val BARANGAY = "BARANGAY"
        private const val CITY = "CITY"
        private const val PROVINCE = "PROVINCE"
        private const val COUNT = 0
        private const val FORM_ID = "FORM_ID"

        private const val ID_COPY = "ID_COPY"
        private const val PREFILLED_COPY = false
        private const val ORGANIZATION_COPY = "ORGANIZATION_COPY"
        private const val SITIO_COPY = "SITIO_COPY"
        private const val BARANGAY_COPY = "BARANGAY_COPY"
        private const val CITY_COPY = "CITY_COPY"
        private const val PROVINCE_COPY = "PROVINCE_COPY"
        private const val COUNT_COPY = 100
        private const val FORM_ID_COPY = "FORM_ID_COPY"
    }

    override fun initData(): BaselineData {
        return BaselineData(
            ID,
            PREFILLED,
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
            COUNT,
            FORM_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(PREFILLED, data.usePrefilled)
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
        assertEquals(FORM_ID, data.formId)
    }

    override fun copyFrom() {
        val copyData = BaselineData(
            ID_COPY,
            PREFILLED_COPY,
            ORGANIZATION_COPY,
            SITIO_COPY,
            BARANGAY_COPY,
            CITY_COPY,
            PROVINCE_COPY,
            COUNT_COPY,
            COUNT_COPY + 1,
            COUNT_COPY + 2,
            COUNT_COPY + 3,
            COUNT_COPY + 4,
            COUNT_COPY + 5,
            COUNT_COPY + 6,
            COUNT_COPY + 7,
            COUNT_COPY + 8,
            COUNT_COPY + 9,
            COUNT_COPY + 10,
            COUNT_COPY + 11,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY,
            FORM_ID_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData, data)
        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.usePrefilled, data.usePrefilled)
        assertEquals(copyData.organization, data.organization)
        assertEquals(copyData.sitio, data.sitio)
        assertEquals(copyData.barangay, data.barangay)
        assertEquals(copyData.city, data.city)
        assertEquals(copyData.province, data.province)
        assertEquals(copyData.male_0to5, data.male_0to5)
        assertEquals(copyData.female_0to5, data.female_0to5)
        assertEquals(copyData.male_6to9, data.male_6to9)
        assertEquals(copyData.female_6to9, data.female_6to9)
        assertEquals(copyData.male_10to12, data.male_10to12)
        assertEquals(copyData.female_10to12, data.female_10to12)
        assertEquals(copyData.male_13to17, data.male_13to17)
        assertEquals(copyData.female_13to17, data.female_13to17)
        assertEquals(copyData.male_18to59, data.male_18to59)
        assertEquals(copyData.female_18to59, data.female_18to59)
        assertEquals(copyData.male_60plus, data.male_60plus)
        assertEquals(copyData.female_60plus, data.female_60plus)
        assertEquals(copyData.totalFamilies, data.totalFamilies)
        assertEquals(copyData.totalHouseholds, data.totalHouseholds)
        assertEquals(copyData.shelterConcrete, data.shelterConcrete)
        assertEquals(copyData.shelterSemiConcrete, data.shelterSemiConcrete)
        assertEquals(copyData.shelterLightMaterials, data.shelterLightMaterials)
        assertNotEquals(copyData.formId, data.formId)
    }

    @Test
    fun copyFromPrefilled() {
        val copyData = PrefilledData(
            ID_COPY,
            ORGANIZATION_COPY,
            SITIO_COPY,
            BARANGAY_COPY,
            CITY_COPY,
            PROVINCE_COPY,
            COUNT_COPY,
            COUNT_COPY + 1,
            COUNT_COPY + 2,
            COUNT_COPY + 3,
            COUNT_COPY + 4,
            COUNT_COPY + 5,
            COUNT_COPY + 6,
            COUNT_COPY + 7,
            COUNT_COPY + 8,
            COUNT_COPY + 9,
            COUNT_COPY + 10,
            COUNT_COPY + 11,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY,
            COUNT_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData, data)
        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.organization, data.organization)
        assertEquals(copyData.sitio, data.sitio)
        assertEquals(copyData.barangay, data.barangay)
        assertEquals(copyData.city, data.city)
        assertEquals(copyData.province, data.province)
        assertEquals(copyData.male_0to5, data.male_0to5)
        assertEquals(copyData.female_0to5, data.female_0to5)
        assertEquals(copyData.male_6to9, data.male_6to9)
        assertEquals(copyData.female_6to9, data.female_6to9)
        assertEquals(copyData.male_10to12, data.male_10to12)
        assertEquals(copyData.female_10to12, data.female_10to12)
        assertEquals(copyData.male_13to17, data.male_13to17)
        assertEquals(copyData.female_13to17, data.female_13to17)
        assertEquals(copyData.male_18to59, data.male_18to59)
        assertEquals(copyData.female_18to59, data.female_18to59)
        assertEquals(copyData.male_60plus, data.male_60plus)
        assertEquals(copyData.female_60plus, data.female_60plus)
        assertEquals(copyData.totalFamilies, data.totalFamilies)
        assertEquals(copyData.totalHouseholds, data.totalHouseholds)
        assertEquals(copyData.shelterConcrete, data.shelterConcrete)
        assertEquals(copyData.shelterSemiConcrete, data.shelterSemiConcrete)
        assertEquals(copyData.shelterLightMaterials, data.shelterLightMaterials)
    }
}