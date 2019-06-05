package com.cpu.quikdata.feature.createform.evacuationinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.evacuation.EvacuationItem
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class EvacuationInfoRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<EvacuationItemDetails>(application) {

    private val mFormId = formId
    private val mEvacuationInfo = mDatabase.evacuationItemDao().getByFormId(mFormId)

    val evacuationInfo: LiveData<List<EvacuationItemDetails>>
        get() = mEvacuationInfo

    override fun updateData(data: EvacuationItemDetails) {
    }

    override fun deleteData(data: EvacuationItemDetails) {
        runOnIoThread {
            mDatabase.evacuationItemDao().delete(data.item!!)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val evacuationItem = EvacuationItem(
                id = id,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.evacuationItemDao().insert(evacuationItem)

            val siteInfo = SiteInfo(
                id = generateId(),
                name = "Evacuation Area",
                evacuationDate = LocalDate.now().toDateTimeAtStartOfDay().millis,
                evacuationId = id)
            mDatabase.siteInfoDao().insert(siteInfo)

            for (i in 0 until AgeCategories.values().size) {
                val row = EvacuationAgeRow(
                    id = generateId(),
                    type = i,
                    evacuationId = id)
                mDatabase.evacuationAgeRowDao().insert(row)
            }

            val evacuationFacilities = EvacuationFacilities(
                id = generateId(),
                evacuationId = id)
            mDatabase.evacuationFacilitiesDao().insert(evacuationFacilities)

            val evacuationWash = EvacuationWash(
                id = generateId(),
                evacuationId = id)
            mDatabase.evacuationWashDao().insert(evacuationWash)

            val evacuationProtection = EvacuationProtection(
                id = generateId(),
                evacuationId = id)
            mDatabase.evacuationProtectionDao().insert(evacuationProtection)

            val evacuationCoping = EvacuationCoping(
                id = generateId(),
                evacuationId = id)
            mDatabase.evacuationCopingDao().insert(evacuationCoping)
        }
    }
}