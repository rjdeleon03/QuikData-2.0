package com.cpu.quikdata.data.watersanitationinfo.washassistance

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "wash_assistance_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class WashAssistanceRow(@PrimaryKey(autoGenerate = false)
                             override val id: String = "",
                             var organizationAgency: String = "",
                             var assistanceType: String = "",
                             var dateReceived: Long = 0L,
                             var quantity: String = "",
                             var beneficiariesMen: Int = 0,
                             var beneficiariesWomen: Int = 0,
                             var beneficiariesBoys: Int = 0,
                             var beneficiariesGirls: Int = 0,
                             val dateCreated: Long = 0L,
                             val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is WashAssistanceRow) return false
        if (this === other) return true
        if (id == other.id &&
            organizationAgency == other.organizationAgency &&
            assistanceType == other.assistanceType &&
            dateReceived == other.dateReceived &&
            quantity == other.quantity &&
            beneficiariesMen == other.beneficiariesMen &&
            beneficiariesWomen == other.beneficiariesWomen &&
            beneficiariesBoys == other.beneficiariesBoys &&
            beneficiariesGirls == other.beneficiariesGirls &&
            dateCreated == other.dateCreated &&
            formId == other.formId)
            return true
        return false
    }
}