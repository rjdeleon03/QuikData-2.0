package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateFormRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFormId = formId
    private val mForm = mDatabase.formDao().getById(mFormId)
    private val mServerRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_FORM)

    val form: LiveData<Form>
        get() = mForm

    private val formId: String
        get() = mForm.value!!.idRemote

    private val isFormAlreadyUploaded: Boolean
         get() = !formId.isBlank()

    // region Submission methods

    fun submitFormDetails() {
        submitFormSection {
            val dao = mDatabase.formDetailsDao()
            val section = dao.getByFormIdNonLive(mFormId)
            if (section.formIdRemote.isBlank()) {
                section.formIdRemote = formId
                dao.update(section)
            }
            mServerRef.child(section.formIdRemote).child(FIREBASE_KEY_FORM_DETAILS).setValue(section)
        }
    }

    fun submitGeneralInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.calamityInfoDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.formIdRemote.isBlank()) {
                    section.formIdRemote = formId
                    dao.update(section)
                }
                mServerRef.saveFormSection(section.formIdRemote,
                    FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_CALAMITY_INFO, section)
            }
            run {
                val dao = mDatabase.populationRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_POPULATION, section)
                }
            }
            run {
                val dao = mDatabase.familiesDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.formIdRemote.isBlank()) {
                    section.formIdRemote = formId
                    dao.update(section)
                }
                mServerRef.saveFormSection(section.formIdRemote,
                    FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_FAMILIES, section)
            }
            run {
                val dao = mDatabase.vulnerableRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_VULNERABLE, section)
                }
            }
            run {
                val dao = mDatabase.casualtiesRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_CASUALTIES, section)
                }
            }
            run {
                val dao = mDatabase.causeOfDeathRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_GENERAL_INFO, FIREBASE_KEY_CAUSE_OF_DEATH, section)
                }
            }
            run {
                val dao = mDatabase.infrastructureDamageRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.child(section[0].formIdRemote)
                        .child(FIREBASE_KEY_GENERAL_INFO).child(FIREBASE_KEY_INFRASTRUCTURE).setValue(section)
                }
            }
        }

    }

    fun submitShelterInformation() {
        submitFormSection {
            run {
                val dao = mDatabase.houseDamageRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_SHELTER_INFO, FIREBASE_KEY_HOUSE_DAMAGE, section)
                }
            }
            run {
                val dao = mDatabase.shelterCopingDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.formIdRemote.isBlank()) {
                    section.formIdRemote = formId
                    dao.update(section)
                }
                mServerRef.saveFormSection(section.formIdRemote,
                    FIREBASE_KEY_SHELTER_INFO, FIREBASE_KEY_SHELTER_COPING, section)
            }
            run {
                val dao = mDatabase.shelterNeedsRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_SHELTER_INFO, FIREBASE_KEY_SHELTER_NEEDS, section)
                }
            }
            run {
                val dao = mDatabase.shelterAssistanceRowDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.isNotEmpty() && section[0].formIdRemote.isBlank()) {
                    section.forEach { it.formIdRemote = formId }
                    dao.update(section)
                    mServerRef.saveFormSection(section[0].formIdRemote,
                        FIREBASE_KEY_SHELTER_INFO, FIREBASE_KEY_SHELTER_ASSISTANCE, section)
                }
            }
            run {
                val dao = mDatabase.shelterGapsDao()
                val section = dao.getByFormIdNonLive(mFormId)
                if (section.formIdRemote.isBlank()) {
                    section.formIdRemote = formId
                    dao.update(section)
                }
                mServerRef.saveFormSection(section.formIdRemote,
                    FIREBASE_KEY_SHELTER_INFO, FIREBASE_KEY_SHELTER_GAPS, section)
            }
        }
    }

    // endregion

    // region Private methods

    private fun submitFormSection(f: () -> Unit) {
        runOnIoThread {
            if (isFormAlreadyUploaded) {
                f.invoke()
            } else {
                submitData(f)
            }
        }
    }

    private fun submitData(f: () -> Unit) {

        // Retrieve form from database
        val dao = mDatabase.formDao()
        val form = mForm.value!!

        // Save to server
        val pushRef = if (!form.idRemote.isEmpty()) {
            mServerRef.child(form.idRemote)
        } else {
            mServerRef.push()
        }

        val task = pushRef.setValue(form)
        task.addOnCompleteListener {
            if (form.idRemote.isEmpty()) {
                runOnIoThread {

                    // Upon successful save, update form with server ID
                    val key = pushRef.key
                    if (key != null) {
                        form.idRemote = key
                        dao.update(form)
                    }
                    f.invoke()
                }
            }
        }
    }

    // endregion

    // region Extensions

    private fun DatabaseReference.saveFormSection(formId: String,
                                                  sectionKey: String,
                                                  subsectionKey: String,
                                                  data: Any) {
        this.child(formId)
            .child(sectionKey)
            .child(subsectionKey).setValue(data)
    }

    // endregion
}