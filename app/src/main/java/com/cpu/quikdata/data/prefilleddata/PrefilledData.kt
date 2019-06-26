package com.cpu.quikdata.data.prefilleddata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cpu.quikdata.PREFILLED_DATA_ID
import com.cpu.quikdata.base.BaseDataWithId

@Entity(tableName = "prefilled_data")
data class PrefilledData(@PrimaryKey(autoGenerate = false)
                         override val id: String = PREFILLED_DATA_ID,
                         var organization: String = "",
                         var sitio: String = "",
                         var barangay: String = "",
                         var city: String = "",
                         var province: String = "",
                         var male_0to5: Int = 0,
                         var female_0to5: Int = 0,
                         var male_6to9: Int = 0,
                         var female_6to9: Int = 0,
                         var male_10to12: Int = 0,
                         var female_10to12: Int = 0,
                         var male_13to17: Int = 0,
                         var female_13to17: Int = 0,
                         var male_18to59: Int = 0,
                         var female_18to59: Int = 0,
                         var male_60plus: Int = 0,
                         var female_60plus: Int = 0,
                         var totalFamilies: Int = 0,
                         var totalHouseholds: Int = 0,
                         var shelterConcrete: Int = 0,
                         var shelterSemiConcrete: Int = 0,
                         var shelterLightMaterials: Int = 0) : BaseDataWithId