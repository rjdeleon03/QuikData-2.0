package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class FoodSecurityAssistanceRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<FoodSecurityAssistanceRow>(application) {

    private val mFormId = formId
    private val mFoodSecurityAssistance = mDatabase.foodSecurityAssistanceRowDao().getByFormId(formId)

    val foodSecurityAssistance: LiveData<List<FoodSecurityAssistanceRow>>
        get() = mFoodSecurityAssistance

    override fun updateData(data: FoodSecurityAssistanceRow) {
        runOnIoThread {
            mDatabase.foodSecurityAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val dateTodayInMillis = LocalDate.now().toDateTimeAtStartOfDay().millis
            val row = FoodSecurityAssistanceRow(id = generateId(),
                dateReceived = dateTodayInMillis,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.foodSecurityAssistanceRowDao().insert(row)
        }
    }

    override fun deleteData(data: FoodSecurityAssistanceRow) {
        runOnIoThread {
            mDatabase.foodSecurityAssistanceRowDao().delete(data)
        }
    }
}