package com.cpu.quikdata.data.baselinedata

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.prefilleddata.PrefilledData


@Entity(tableName = "baseline_data",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class BaselineData(@PrimaryKey(autoGenerate = false)
                        val id: String = "",
                        var usePrefilled: Boolean = true,
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
                        var shelterLightMaterials: Int = 0,
                        val formId: String = "") {

    fun copyFrom(data: PrefilledData) {
        organization = data.organization
        sitio = data.sitio
        barangay = data.barangay
        city = data.city
        province = data.province
        male_0to5 = data.male_0to5
        female_0to5 = data.female_0to5
        male_6to9 = data.male_6to9
        female_6to9 = data.female_6to9
        male_10to12 = data.male_10to12
        female_10to12 = data.female_10to12
        male_13to17 = data.male_13to17
        female_13to17 = data.female_13to17
        male_18to59 = data.male_18to59
        female_18to59 = data.female_18to59
        male_60plus = data.male_60plus
        female_60plus = data.female_60plus
        totalFamilies = data.totalFamilies
        totalHouseholds = data.totalHouseholds
        shelterConcrete = data.shelterConcrete
        shelterSemiConcrete = data.shelterSemiConcrete
        shelterLightMaterials = data.shelterLightMaterials
    }

    fun copyFrom(data: BaselineData) {
        usePrefilled = data.usePrefilled
        organization = data.organization
        sitio = data.sitio
        barangay = data.barangay
        city = data.city
        province = data.province
        male_0to5 = data.male_0to5
        female_0to5 = data.female_0to5
        male_6to9 = data.male_6to9
        female_6to9 = data.female_6to9
        male_10to12 = data.male_10to12
        female_10to12 = data.female_10to12
        male_13to17 = data.male_13to17
        female_13to17 = data.female_13to17
        male_18to59 = data.male_18to59
        female_18to59 = data.female_18to59
        male_60plus = data.male_60plus
        female_60plus = data.female_60plus
        totalFamilies = data.totalFamilies
        totalHouseholds = data.totalHouseholds
        shelterConcrete = data.shelterConcrete
        shelterSemiConcrete = data.shelterSemiConcrete
        shelterLightMaterials = data.shelterLightMaterials
    }
}