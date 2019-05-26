package com.cpu.quikdata.feature.main.newforms

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.population.Population
import com.cpu.quikdata.data.generalinfo.population.row.PopulationRow
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
            val form = Form(formId)
            mDatabase.formDao().insert(form)

            // region Form details

            val formDetails = FormDetails(id = generateId(),
                assessmentDate = LocalDate.now().toDateTimeAtStartOfDay().millis,
                formId = formId)
            mDatabase.formDetailsDao().insert(formDetails)

            // endregion

            // region General information

            val calamityInfo = CalamityInfo(id = generateId(), formId = formId)
            mDatabase.calamityInfoDao().insert(calamityInfo)

            val populationId = generateId()
            val population = Population(id = populationId, formId = formId)
            mDatabase.populationDao().insert(population)
            for (i in 0..AgeCategories.values().size) {
                val row = PopulationRow(id = generateId(),
                    type = i,
                    populationId = populationId)
                mDatabase.populationRowDao().insert(row)
            }

            // endregion
        }
    }


}