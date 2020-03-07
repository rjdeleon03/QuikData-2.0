package com.cpu.quikdata.feature.createform.evacuationinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.EvacuationItem
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import javax.inject.Inject

class EvacuationInfoRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String
) : BaseCreatableDataRepository<EvacuationItemDetails>() {

    private val mEvacuationInfo = mDatabase.evacuationItemDao().getByFormIdForDisplay(mFormId)

    val evacuationInfo: LiveData<List<EvacuationItemDetails>>
        get() = mEvacuationInfo

    override suspend fun updateData(data: EvacuationItemDetails) {
    }

    override suspend fun deleteData(data: EvacuationItemDetails) {
        mDatabase.evacuationItemDao().delete(data.item!!)
    }

    override suspend fun createData(id: String) {
        val evacuationItem = EvacuationItem(
            id = id,
            dateCreated = getDateTimeNowInLong(),
            formId = mFormId
        )
        mDatabase.evacuationItemDao().insert(evacuationItem)

        val siteInfo = SiteInfo(
            id = generateId(),
            evacuationDate = getDateNowInLong(),
            evacuationId = id
        )
        mDatabase.siteInfoDao().insert(siteInfo)

        for (i in AgeCategories.values().indices) {
            val row = EvacuationAgeRow(
                id = generateId(),
                type = i,
                evacuationId = id
            )
            mDatabase.evacuationAgeRowDao().insert(row)
        }

        val evacuationFacilities = EvacuationFacilities(
            id = generateId(),
            evacuationId = id
        )
        mDatabase.evacuationFacilitiesDao().insert(evacuationFacilities)

        val evacuationWash = EvacuationWash(
            id = generateId(),
            evacuationId = id
        )
        mDatabase.evacuationWashDao().insert(evacuationWash)

        val evacuationProtection = EvacuationProtection(
            id = generateId(),
            evacuationId = id
        )
        mDatabase.evacuationProtectionDao().insert(evacuationProtection)

        val evacuationCoping = EvacuationCoping(
            id = generateId(),
            evacuationId = id
        )
        mDatabase.evacuationCopingDao().insert(evacuationCoping)
    }
}