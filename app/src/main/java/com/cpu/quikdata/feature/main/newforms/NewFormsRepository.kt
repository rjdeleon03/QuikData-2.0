package com.cpu.quikdata.feature.main.newforms

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.HouseCategories
import com.cpu.quikdata.common.InfraCategories
import com.cpu.quikdata.common.MaterialCategories
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.utils.generateId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.joda.time.LocalDate

class NewFormsRepository(application: Application) {

    private val mDatabase = AppDatabase.get(application)
    private val mNewForms = mDatabase.formDao().getAll()

    val newForms: LiveData<List<Form>>
        get() = mNewForms

    fun createNewForm(formId: String) {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            val dateNowInLong = LocalDate.now().toDateTimeAtStartOfDay().millis
            val form = Form(formId)
            mDatabase.formDao().insert(form)

            // region Form details

            val formDetails = FormDetails(id = generateId(),
                assessmentDate = dateNowInLong,
                formId = formId)
            mDatabase.formDetailsDao().insert(formDetails)

            // endregion

            // region General information

            val calamityInfo = CalamityInfo(id = generateId(),
                formId = formId,
                occurrenceDate = dateNowInLong)
            mDatabase.calamityInfoDao().insert(calamityInfo)

            for (i in 0 until AgeCategories.values().size) {
                val row = PopulationRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.populationRowDao().insert(row)
            }

            val families = Families(id = generateId(), formId = formId)
            mDatabase.familiesDao().insert(families)

            for (i in 0 until AgeCategories.values().size) {
                val row = VulnerableRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.vulnerableRowDao().insert(row)
            }

            for (i in 0 until AgeCategories.values().size) {
                val row = CasualtiesRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.casualtiesRowDao().insert(row)
            }

            for (i in 0 until AgeCategories.values().size) {
                val row = CauseOfDeathRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.causeOfDeathRowDao().insert(row)
            }

            for (i in 0 until InfraCategories.values().size) {
                val row = InfrastructureDamageRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.infrastructureDamageRowDao().insert(row)
            }

            // endregion

            // region Shelter and non-food information

            for (i in 0 until HouseCategories.values().size) {
                val row = HouseDamageRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.houseDamageRowDao().insert(row)
            }

            val shelterCoping = ShelterCoping(id = generateId(), formId = formId)
            mDatabase.shelterCopingDao().insert(shelterCoping)

            for (i in 0 until MaterialCategories.values().size) {
                val row = ShelterNeedsRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                mDatabase.shelterNeedsRowDao().insert(row)
            }

            val shelterGaps = ShelterGaps(id = generateId(), formId = formId)
            mDatabase.shelterGapsDao().insert(shelterGaps)

            val foodSecurityImpact = FoodSecurityImpact(id = generateId(), formId = formId)
            mDatabase.foodSecurityImpactDao().insert(foodSecurityImpact)

            val foodSecurityCoping = FoodSecurityCoping(id = generateId(), formId = formId)
            mDatabase.foodSecurityCopingDao().insert(foodSecurityCoping)

            // endregion
        }
    }


}