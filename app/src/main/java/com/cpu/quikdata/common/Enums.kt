package com.cpu.quikdata.common

import com.cpu.quikdata.R

enum class AgeCategories {
    AGE_O_TO_5,
    AGE_6_TO_9,
    AGE_10_TO_12,
    AGE_13_TO_17,
    AGE_18_TO_59,
    AGE_60_PLUS;

    fun getStringId(): Int {
        return when(this) {
            AGE_O_TO_5 -> R.string.text_age_0to5
            AGE_6_TO_9 -> R.string.text_age_6to9
            AGE_10_TO_12 -> R.string.text_age_10to12
            AGE_13_TO_17 -> R.string.text_age_13to17
            AGE_18_TO_59 -> R.string.text_age_18to59
            AGE_60_PLUS -> R.string.text_age_60plus
        }
    }

    companion object {

        fun getStringId(idx: Int): Int {
            return AgeCategories.values()[idx].getStringId()
        }
    }
}

enum class InfraCategories {
    SCHOOL,
    CHURCH,
    COVERED_COURT,
    BRGY_HALL,
    BRGY_HEALTH_STATION,
    EVACUATION_CENTER,
    BRIDGES,
    ROADS,
    MARKET,
    ELECTRICITY,
    WATER,
    HOSPITAL,
    SEAPORT_AIRPORT,
    COMMUNICATION_LINES,
    LIVELIHOOD_FACILITIES,
    OTHERS;

    fun getStringId(): Int {
        return when(this) {
            SCHOOL -> R.string.text_school
            CHURCH -> R.string.text_church
            COVERED_COURT -> R.string.text_covered_court
            BRGY_HALL -> R.string.text_brgy_hall
            BRGY_HEALTH_STATION -> R.string.text_brgy_health_station
            EVACUATION_CENTER -> R.string.text_evacuation_center
            BRIDGES -> R.string.text_bridges
            ROADS -> R.string.text_roads
            MARKET -> R.string.text_market
            ELECTRICITY -> R.string.text_electricity
            WATER -> R.string.text_water
            HOSPITAL -> R.string.text_hospital
            SEAPORT_AIRPORT -> R.string.text_seaport_airport
            COMMUNICATION_LINES -> R.string.text_communication_lines
            LIVELIHOOD_FACILITIES -> R.string.text_livelihood_facilities
            OTHERS -> R.string.text_others
        }
    }

    companion object {

        fun getStringId(idx: Int): Int {
            return InfraCategories.values()[idx].getStringId()
        }
    }
}