package com.cpu.quikdata.common

import android.net.Uri
import com.cpu.quikdata.*
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.utils.runOnIoThread
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseHelper {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val mStorage = FirebaseStorage.getInstance()

    // region Submission methods

    // TODO: Add failure listener for submitFormDetailsFirst
    fun submitBasicData(database: AppDatabase, formId: String) {
        submitFormDetailsFirst(database, formId, { db, id ->
            submitGeneralInformation(db, id, {}, {})
            submitCaseStories(db, id, {}, {})
        }, {})
    }

    fun submitAllData(database: AppDatabase, formId: String) {
        submitFormDetailsFirst(database, formId, { db, id ->
            submitGeneralInformation(db, id, {}, {})
            submitShelterInformation(db, id, {}, {})
            submitFoodSecurity(db, id, {}, {})
            submitLivelihoods(db, id, {}, {})
            submitHealthInformation(db, id, {}, {})
            submitWashInformation(db, id, {}, {})
            submitEvacuationInformation(db, id, {}, {})
            submitCaseStories(db, id, {}, {})
        }, {})
    }

    private fun submitGeneralInformation(database: AppDatabase, formId: String,
                                         onSuccessListener: () -> Unit,
                                         onFailureListener: () -> Unit) {

        submitSection(7, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.calamityInfoDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_CALAMITY_INFO, section.id, section, sc, fl)
            }
            run {
                val dao = database.populationRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_POPULATION, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.familiesDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_FAMILIES, section.id, section, sc, fl)
            }
            run {
                val dao = database.vulnerableRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_VULNERABLE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.casualtiesRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_CASUALTIES, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.causeOfDeathRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_CAUSE_OF_DEATH, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.infrastructureDamageRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_INFRASTRUCTURE, it.id, it, scl, fl)
                }
            }
        }
    }

    private fun submitShelterInformation(database: AppDatabase, formId: String,
                                         onSuccessListener: () -> Unit,
                                         onFailureListener: () -> Unit) {
        submitSection(5, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.houseDamageRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_HOUSE_DAMAGE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.shelterCopingDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_SHELTER_COPING, section.id, section, sc, fl)
            }
            run {
                val dao = database.shelterNeedsRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_SHELTER_NEEDS, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.shelterAssistanceRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_SHELTER_ASSISTANCE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.shelterGapsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_SHELTER_GAPS, section.id, section, sc, fl)
            }
        }
    }

    private fun submitFoodSecurity(database: AppDatabase, formId: String,
                                   onSuccessListener: () -> Unit,
                                   onFailureListener: () -> Unit) {

        submitSection(5, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.foodSecurityImpactDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_FOOD_IMPACT, section.id, section, sc, fl)
            }
            run {
                val dao = database.foodSecurityCopingDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_FOOD_COPING, section.id, section, sc, fl)
            }
            run {
                val dao = database.foodSecurityNeedsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_FOOD_NEEDS, section.id, section, sc, fl)
            }
            run {
                val dao = database.foodSecurityAssistanceRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_FOOD_ASSISTANCE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.foodSecurityGapsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_FOOD_GAPS, section.id, section, sc, fl)
            }
        }
    }

    private fun submitLivelihoods(database: AppDatabase, formId: String,
                                  onSuccessListener: () -> Unit,
                                  onFailureListener: () -> Unit) {

        submitSection(7, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.incomeBeforeRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_INCOME_BEFORE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.incomeAfterRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_INCOME_AFTER, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.estimatedDamageRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_ESTIMATED_DAMAGE, it.row!!.id, section, scl, fl)
                }
            }
            run {
                val dao = database.livelihoodsCopingDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_LIVELIHOODS_COPING, section.id, section, sc, fl)
            }
            run {
                val dao = database.livelihoodsNeedsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_LIVELIHOODS_NEEDS, section.id, section, sc, fl)
            }
            run {
                val dao = database.livelihoodsAssistanceRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_LIVELIHOODS_ASSISTANCE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.livelihoodsGapsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_LIVELIHOODS_GAPS, section.id, section, sc, fl)
            }
        }
    }

    private fun submitHealthInformation(database: AppDatabase, formId: String,
                                        onSuccessListener: () -> Unit,
                                        onFailureListener: () -> Unit) {

        submitSection(6, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.diseasesRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_DISEASES, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.specialNeedsRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_SPECIAL_NEEDS, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.psychosocialRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_PSYCHOSOCIAL, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.healthCopingDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_HEALTH_COPING, section.id, section, sc, fl)
            }
            run {
                val dao = database.healthAssistanceRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_HEALTH_ASSISTANCE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.healthGapsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_HEALTH_GAPS, section.id, section, sc, fl)
            }
        }
    }

    private fun submitWashInformation(database: AppDatabase, formId: String,
                                      onSuccessListener: () -> Unit,
                                      onFailureListener: () -> Unit) {

        submitSection(4, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.washConditionsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_WASH_CONDITIONS, section.id, section, sc, fl)
            }
            run {
                val dao = database.washCopingDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_WASH_COPING, section.id, section, sc, fl)
            }
            run {
                val dao = database.washAssistanceRowDao()
                val section = dao.getByFormIdNonLive(formId)
                saveListData(section, sc) { it, scl ->
                    saveData(FIREBASE_KEY_WASH_ASSISTANCE, it.id, it, scl, fl)
                }
            }
            run {
                val dao = database.washGapsDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_WASH_GAPS, section.id, section, sc, fl)
            }
        }
    }

    private fun submitEvacuationInformation(database: AppDatabase, formId: String,
                                            onSuccessListener: () -> Unit,
                                            onFailureListener: () -> Unit) {
        submitSection(1, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.evacuationItemDao()
                val section = dao.getByFormIdNonLive(formId)
                if (section.isNotEmpty()) {
                    saveListData(section, sc) { it, scl ->
                        saveData(FIREBASE_KEY_EVACUATION, it.root!!.id, it, scl, fl)
                    }
                }
            }
        }
    }

    private fun submitCaseStories(database: AppDatabase, formId: String,
                                  onSuccessListener: () -> Unit,
                                  onFailureListener: () -> Unit) {
        submitSection(2, onSuccessListener, onFailureListener) { sc, fl ->
            run {
                val dao = database.caseStoriesDao()
                val section = dao.getByFormIdNonLive(formId)
                saveData(FIREBASE_KEY_CASE_STORIES, section.root!!.id, section, sc, fl)
                if (section.images != null) {
                    saveListData(section.images!!, sc) { it, scl ->
                        mStorage.reference.child("images/${it.id}")
                            .putFile(Uri.parse(it.uri))
                            .addOnSuccessListener { scl() }
                            .addOnFailureListener { fl() }
                    }
                }
            }
        }
    }

    // endregion

    // region Private methods

    private fun submitFormDetailsFirst(database: AppDatabase, formId: String,
                                       f: ((AppDatabase, String) -> Unit)? = null,
                                       onFailureListener: () -> Unit) {
        runOnIoThread {
            // Retrieve form
            val formComplete = database.formDao().getFormDataNonLive(formId)
            mFirestore.collection(FIREBASE_KEY_FORM).document(formId).get().continueWith {
                saveData(FIREBASE_KEY_FORM, formComplete.form!!.id, formComplete, {
                    runOnIoThread {
                        f?.invoke(database, formId)
                    }
                }, onFailureListener)
            }
        }
    }

    // endregion

    // region Utils

    private fun saveData(collectionName:String, id: String, data: Any,
                         onSuccessListener: () -> Unit,
                         onFailureListener: () -> Unit) : Task<Void> =

        mFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { onSuccessListener() }
            .addOnFailureListener { onFailureListener()  }

    private fun <T: Any> saveListData(data: List<T>,
                                      onSuccessListener: () -> Unit,
                                      saveAction: (T, () -> Unit) -> Any) {

        val targetCount = data.size
        var successCount = 0
        val successCounter = {
            successCount++
            if (successCount == targetCount) onSuccessListener()
        }

        data.forEach {
            saveAction(it, successCounter)
        }
    }

    private fun submitSection(targetCount: Int,
                              onSuccessListener: () -> Unit,
                              onFailureListener: () -> Unit,
                              submissionTask: (() -> Unit, () -> Unit) -> Unit) {

        var successCount = 0
        val successCounter = {
            successCount++
            if (successCount == targetCount) {
                onSuccessListener()
            }
        }

        submissionTask(successCounter, onFailureListener)
    }

    // endregion
}