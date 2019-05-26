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