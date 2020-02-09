package com.cpu.quikdata.feature.main.newforms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.common.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormComplete
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageRow
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageType
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import com.cpu.quikdata.di.component.repository.DaggerNewFormsRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.di.module.FirebaseHelperModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewFormsRepository(application: QuikDataApp) {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var firebaseHelper: FirebaseHelper

    private val mNewForms: LiveData<List<FormComplete>>
    
    private val mSaveResult = MediatorLiveData<ProgressNotification>()

    init {
        DaggerNewFormsRepositoryComponent.builder()
            .databaseModule(DatabaseModule(application))
            .firebaseHelperModule(FirebaseHelperModule())
            .build()
            .inject(this)

        mNewForms = database.formDao().getAllActual()
    }


    val newForms: LiveData<List<FormComplete>>
        get() = mNewForms

    val saveResult: LiveData<ProgressNotification>
        get() = mSaveResult

    fun createNewForm(formId: String) {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            val dateNowInLong = getDateNowInLong()
            val form = Form(formId, getDateTimeNowInLong(), getDateTimeNowInLong())
            database.formDao().insert(form)

            // region Form details

            val formDetails = FormDetails(id = generateId(),
                assessmentDate = dateNowInLong,
                formId = formId)
            database.formDetailsDao().insert(formDetails)

            val baselineData = BaselineData(id = generateId(),
                formId = formId)
            database.baselineDataDao().insert(baselineData)

            // endregion

            // region General information

            val calamityInfo = CalamityInfo(id = generateId(),
                formId = formId,
                occurrenceDate = dateNowInLong)
            database.calamityInfoDao().insert(calamityInfo)

            for (i in AgeCategories.values().indices) {
                val row = PopulationRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.populationRowDao().insert(row)
            }

            val families = Families(id = generateId(), formId = formId)
            database.familiesDao().insert(families)

            for (i in AgeCategories.values().indices) {
                val row = VulnerableRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.vulnerableRowDao().insert(row)
            }

            for (i in AgeCategories.values().indices) {
                val row = CasualtiesRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.casualtiesRowDao().insert(row)
            }

            for (i in AgeCategories.values().indices) {
                val row = CauseOfDeathRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.causeOfDeathRowDao().insert(row)
            }

            for (i in InfraCategories.values().indices) {
                val row = InfrastructureDamageRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.infrastructureDamageRowDao().insert(row)
            }

            // endregion

            // region Case stories

            val caseStories = CaseStories(id = generateId(), formId = formId)
            database.caseStoriesDao().insert(caseStories)

            // endregion

            // region Shelter and non-food information

            for (i in HouseCategories.values().indices) {
                val row = HouseDamageRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.houseDamageRowDao().insert(row)
            }

            val shelterCoping = ShelterCoping(id = generateId(), formId = formId)
            database.shelterCopingDao().insert(shelterCoping)

            for (i in MaterialCategories.values().indices) {
                val row = ShelterNeedsRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.shelterNeedsRowDao().insert(row)
            }

            val shelterGaps = ShelterGaps(id = generateId(), formId = formId)
            database.shelterGapsDao().insert(shelterGaps)

            // endregion

            // region Food security information

            val foodSecurityImpact = FoodSecurityImpact(id = generateId(), formId = formId)
            database.foodSecurityImpactDao().insert(foodSecurityImpact)

            val foodSecurityCoping = FoodSecurityCoping(id = generateId(), formId = formId)
            database.foodSecurityCopingDao().insert(foodSecurityCoping)

            val foodSecurityNeeds = FoodSecurityNeeds(id = generateId(), formId = formId)
            database.foodSecurityNeedsDao().insert(foodSecurityNeeds)

            val foodSecurityGaps = FoodSecurityGaps(id = generateId(), formId = formId)
            database.foodSecurityGapsDao().insert(foodSecurityGaps)

            // endregion

            // region Livelihoods information

            for (i in LivelihoodCategories.values().indices) {
                val estimatedDamageId = generateId()
                val row = EstimatedDamageRow(
                    id = estimatedDamageId,
                    type = i,
                    formId = formId
                )
                database.estimatedDamageRowDao().insert(row)

                val subcategories = LivelihoodCategories.values()[i].getSubcategories()
                for (j in 0 until subcategories.size) {

                    val subRow = EstimatedDamageType(
                        id = generateId(),
                        type = subcategories[j].ordinal,
                        estimatedDamageId = estimatedDamageId
                    )
                    database.estimatedDamageTypeDao().insert(subRow)
                }
            }

            val livelihoodsCoping = LivelihoodsCoping(id = generateId(), formId = formId)
            database.livelihoodsCopingDao().insert(livelihoodsCoping)

            val livelihoodsNeeds = LivelihoodsNeeds(id = generateId(), formId = formId)
            database.livelihoodsNeedsDao().insert(livelihoodsNeeds)

            val livelihoodsGaps = LivelihoodsGaps(id = generateId(), formId = formId)
            database.livelihoodsGapsDao().insert(livelihoodsGaps)

            // endregion

            // region Health information

            for (i in AgeCategories.values().indices) {
                val row = DiseasesRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.diseasesRowDao().insert(row)
            }

            for (i in SpecialNeedsCategories.values().indices) {
                val row = SpecialNeedsRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.specialNeedsRowDao().insert(row)
            }

            for (i in AgeCategories.values().indices) {
                val row = PsychosocialRow(
                    id = generateId(),
                    type = i,
                    formId = formId
                )
                database.psychosocialRowDao().insert(row)
            }

            val healthCoping = HealthCoping(id = generateId(), formId = formId)
            database.healthCopingDao().insert(healthCoping)

            val healthGaps = HealthGaps(id = generateId(), formId = formId)
            database.healthGapsDao().insert(healthGaps)

            // endregion

            // region Water, sanitation, and hygiene

            val washConditions = WashConditions(id = generateId(), formId = formId)
            database.washConditionsDao().insert(washConditions)

            val washCoping = WashCoping(id = generateId(), formId = formId)
            database.washCopingDao().insert(washCoping)

            val washGaps = WashGaps(id = generateId(), formId = formId)
            database.washGapsDao().insert(washGaps)

            // endregion
        }
    }

    fun deleteForm(formComplete: FormComplete) {
        runOnIoThread {
            database.formDao().delete(formComplete.form!!)
        }
    }

    fun submitForm(formId: String) {
        val operation = firebaseHelper.submitAllData(database, formId)
        mSaveResult.addSource(operation) {
            mSaveResult.value = it
            if (it == ProgressNotification.FINISHED ||
                it == ProgressNotification.CANCELLED ||
                it == ProgressNotification.ERROR_OCCURRED) {
                mSaveResult.removeSource(operation)
            }
        }
    }

    fun cancelSubmission() = firebaseHelper.cancelSubmission()
}