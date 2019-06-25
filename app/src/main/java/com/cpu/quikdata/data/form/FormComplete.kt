package com.cpu.quikdata.data.form

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo

class FormComplete {

    @Embedded
    var form: Form? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = FormDetails::class)
    var formDetails: List<FormDetails>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = BaselineData::class)
    var baselineData: List<BaselineData>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = CalamityInfo::class)
    var calamityInfo: List<CalamityInfo>? = null
}