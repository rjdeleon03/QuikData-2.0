package com.cpu.quikdata.feature.createform.evacuationinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.evacuation.EvacuationItem
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDateTime

class EvacuationInfoRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<EvacuationItem>(application) {

    private val mFormId = formId
    private val mEvacuationInfos = mDatabase.evacuationItemDao().getByFormId(mFormId)

    val evacuationInfos: LiveData<List<EvacuationItem>>
        get() = mEvacuationInfos

    override fun updateData(data: EvacuationItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteData(data: EvacuationItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createData(id: String) {
        runOnIoThread {
            val evacuationItem = EvacuationItem(id = id,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.evacuationItemDao().insert(evacuationItem)

            val siteInfo = SiteInfo(id = generateId(),
                evacuationId = id)
            mDatabase.siteInfoDao().insert(siteInfo)
        }
    }
}