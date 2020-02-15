package com.cpu.quikdata.data.evacuation.siteinfo

import com.cpu.quikdata.data.base.BaseCopyOnlyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class SiteInfoTest : BaseCopyOnlyTest<SiteInfo>() {

    companion object {
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val LOCATION = "LOCATION"
        private const val HAVE_MOVED = false
        private const val TYPE = 0
        private const val LGU_DESIGNATED = false
        private const val DISTANCE = "DISTANCE"
        private const val EVACUATION_DATE = 1000L
        private const val SHELTER_SIZE = "SHELTER_SIZE"
        private const val HOUSEHOLDS = 100
        private const val EVACUATION_ID = "EVACUATION_ID"
        private const val ID_COPY = "ID_COPY"
        private const val NAME_COPY = "NAME_COPY"
        private const val LOCATION_COPY = "LOCATION_COPY"
        private const val HAVE_MOVED_COPY = true
        private const val TYPE_COPY = 100
        private const val LGU_DESIGNATED_COPY = true
        private const val DISTANCE_COPY = "DISTANCE_COPY"
        private const val EVACUATION_DATE_COPY = 100000L
        private const val SHELTER_SIZE_COPY = "SHELTER_SIZE_COPY"
        private const val HOUSEHOLDS_COPY = 10000
        private const val EVACUATION_ID_COPY = "EVACUATION_ID_COPY"
    }

    override fun initData(): SiteInfo {
        return SiteInfo(
            ID,
            NAME,
            LOCATION,
            HAVE_MOVED,
            TYPE,
            LGU_DESIGNATED,
            DISTANCE,
            EVACUATION_DATE,
            SHELTER_SIZE,
            HOUSEHOLDS,
            EVACUATION_ID
        )
    }

    override fun getters() {
        assertEquals(ID, data.id)
        assertEquals(NAME, data.name)
        assertEquals(LOCATION, data.location)
        assertEquals(HAVE_MOVED, data.haveMoved)
        assertEquals(TYPE, data.type)
        assertEquals(LGU_DESIGNATED, data.isLguDesignated)
        assertEquals(DISTANCE, data.distanceFromCommunity)
        assertEquals(EVACUATION_DATE, data.evacuationDate)
        assertEquals(SHELTER_SIZE, data.shelterSize)
        assertEquals(HOUSEHOLDS, data.householdsAndFamilies)
        assertEquals(EVACUATION_ID, data.evacuationId)
    }

    override fun copyFrom() {
        val copyData = SiteInfo(
            ID_COPY,
            NAME_COPY,
            LOCATION_COPY,
            HAVE_MOVED_COPY,
            TYPE_COPY,
            LGU_DESIGNATED_COPY,
            DISTANCE_COPY,
            EVACUATION_DATE_COPY,
            SHELTER_SIZE_COPY,
            HOUSEHOLDS_COPY,
            EVACUATION_ID_COPY
        )
        data.copyFrom(copyData)

        assertNotEquals(copyData.id, data.id)
        assertEquals(copyData.name, data.name)
        assertEquals(copyData.location, data.location)
        assertEquals(copyData.haveMoved, data.haveMoved)
        assertEquals(copyData.type, data.type)
        assertEquals(copyData.isLguDesignated, data.isLguDesignated)
        assertEquals(copyData.distanceFromCommunity, data.distanceFromCommunity)
        assertEquals(copyData.evacuationDate, data.evacuationDate)
        assertEquals(copyData.shelterSize, data.shelterSize)
        assertEquals(copyData.householdsAndFamilies, data.householdsAndFamilies)
        assertNotEquals(copyData.evacuationId, data.evacuationId)
    }

}