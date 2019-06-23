package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.runOnIoThread
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

class CreateFormRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFormId = formId
    private val mForm = mDatabase.formDao().getById(mFormId)
    private val mFirestore = FirebaseFirestore.getInstance()

    val form: LiveData<Form>
        get() = mForm

    private val formId: String
        get() = mForm.value!!.id

    fun deleteForm() {
        runOnIoThread {
            val formValue = mForm.value!!
            if (formValue.isTemporary) {
                mDatabase.formDao().delete(formValue)
            }
        }
    }

    fun saveFormAsActual() {
        runOnIoThread {
            val formValue = mForm.value!!
            formValue.isTemporary = false
            mDatabase.formDao().update(formValue)
            submitBasicData()
        }
    }

    // region Submission methods

    private fun submitBasicData() {
        submitFormDetails()
        submitGeneralInformation()
        submitCaseStories()
    }

    fun submitFormDetails() {
        submitFormSection(true)
    }

    fun submitGeneralInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.calamityInfoDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_CALAMITY_INFO, section.id, section)
            }
            run {
                val dao = mDatabase.populationRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_POPULATION, it.id, it)
                }
            }
            run {
                val dao = mDatabase.familiesDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_FAMILIES, section.id, section)
            }
            run {
                val dao = mDatabase.vulnerableRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_VULNERABLE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.casualtiesRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_CASUALTIES, it.id, it)
                }
            }
            run {
                val dao = mDatabase.causeOfDeathRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_CAUSE_OF_DEATH, it.id, it)
                }
            }
            run {
                val dao = mDatabase.infrastructureDamageRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_INFRASTRUCTURE, it.id, it)
                }
            }
        }

    }

    fun submitShelterInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.houseDamageRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_HOUSE_DAMAGE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.shelterCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_SHELTER_COPING, section.id, section)
            }
            run {
                val dao = mDatabase.shelterNeedsRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_SHELTER_NEEDS, it.id, it)
                }
            }
            run {
                val dao = mDatabase.shelterAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_SHELTER_ASSISTANCE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.shelterGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_SHELTER_GAPS, section.id, section)
            }
        }
    }

    fun submitFoodSecurity() {
        submitFormSection {
            run {
                val dao = mDatabase.foodSecurityImpactDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_FOOD_IMPACT, section.id, section)
            }
            run {
                val dao = mDatabase.foodSecurityCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_FOOD_COPING, section.id, section)
            }
            run {
                val dao = mDatabase.foodSecurityNeedsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_FOOD_NEEDS, section.id, section)
            }
            run {
                val dao = mDatabase.foodSecurityAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_FOOD_ASSISTANCE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.foodSecurityGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_FOOD_GAPS, section.id, section)
            }
        }
    }

    fun submitLivelihoods() {
        submitFormSection {
            run {
                val dao = mDatabase.incomeBeforeRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_INCOME_BEFORE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.incomeAfterRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_INCOME_AFTER, it.id, it)
                }
            }
            run {
                val dao = mDatabase.estimatedDamageRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_ESTIMATED_DAMAGE, it.row!!.id, it)
                }
            }
            run {
                val dao = mDatabase.livelihoodsCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_LIVELIHOODS_COPING, section.id, section)
            }
            run {
                val dao = mDatabase.livelihoodsNeedsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_LIVELIHOODS_NEEDS, section.id, section)
            }
            run {
                val dao = mDatabase.livelihoodsAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_LIVELIHOODS_ASSISTANCE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.livelihoodsGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_LIVELIHOODS_GAPS, section.id, section)
            }
        }
    }

    fun submitHealthInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.diseasesRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_DISEASES, it.id, it)
                }
            }
            run {
                val dao = mDatabase.specialNeedsRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_SPECIAL_NEEDS, it.id, it)
                }
            }
            run {
                val dao = mDatabase.psychosocialRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_PSYCHOSOCIAL, it.id, it)
                }
            }
            run {
                val dao = mDatabase.healthCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_HEALTH_COPING, section.id, section)
            }
            run {
                val dao = mDatabase.healthAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_HEALTH_ASSISTANCE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.healthGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_HEALTH_GAPS, section.id, section)
            }
        }
    }

    fun submitWashInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.washConditionsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_WASH_CONDITIONS, section.id, section)
            }
            run {
                val dao = mDatabase.washCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_WASH_COPING, section.id, section)
            }
            run {
                val dao = mDatabase.washAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                section.forEach {
                    saveData(FIREBASE_KEY_WASH_ASSISTANCE, it.id, it)
                }
            }
            run {
                val dao = mDatabase.washGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                saveData(FIREBASE_KEY_WASH_GAPS, section.id, section)
            }
        }
    }

    fun submitEvacuationInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.evacuationItemDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].root!!.formIdRemote.isBlank()) {
                    section.forEach {
                        saveData(FIREBASE_KEY_EVACUATION, it.root!!.id, it)
                    }
                }
            }
        }
    }

    fun submitCaseStories() {

    }

    // endregion

    // region Private methods

    private fun submitFormSection(resubmitForm: Boolean = false, f: (() -> Unit)? = null) {
        runOnIoThread {
            // Retrieve form
            val formComplete = mDatabase.formDao().getFormDataNonLive(formId)
            mFirestore.collection(FIREBASE_KEY_FORM).document(formId).get().continueWith {
                if (!it.result!!.exists() || resubmitForm) {
                    saveData(FIREBASE_KEY_FORM, formComplete.form!!.id, formComplete)
                        .addOnCompleteListener {
                            runOnIoThread {
                                f?.invoke()
                            }
                        }
                } else {
                    runOnIoThread {
                        f?.invoke()
                    }
                }
            }
        }
    }

    // endregion

    // region Utils

    private fun saveData(collectionName:String, id: String, data: Any) : Task<Void> =
        mFirestore.collection(collectionName).document(id).set(data)

    // endregion
}