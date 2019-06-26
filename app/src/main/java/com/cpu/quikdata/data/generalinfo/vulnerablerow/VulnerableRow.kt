package com.cpu.quikdata.data.generalinfo.vulnerablerow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "vulnerable_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class VulnerableRow(@PrimaryKey(autoGenerate = false)
                         override val id: String = "",
                         val type: Int = 0,
                         var pregnant: Int = 0,
                         var lactating: Int = 0,
                         var lgbt: Int = 0,
                         var femaleHeaded: Int = 0,
                         var childHeadedMale: Int = 0,
                         var childHeadedFemale: Int = 0,
                         var indigenousMale: Int = 0,
                         val indigenousFemale: Int = 0,
                         var disabledMale: Int = 0,
                         var disabledFemale: Int = 0,
                         var remarks: String = "",
                         var formIdRemote: String = "",
                         val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VulnerableRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            pregnant == other.pregnant &&
            lactating == other.lactating &&
            lgbt == other.lgbt &&
            femaleHeaded == other.femaleHeaded &&
            childHeadedMale == other.childHeadedMale &&
            childHeadedFemale == other.childHeadedFemale &&
            indigenousMale == other.indigenousMale &&
            indigenousFemale == other.indigenousFemale &&
            disabledMale == other.disabledMale &&
            disabledFemale == other.disabledFemale &&
            remarks == other.remarks &&
            formIdRemote == other.formIdRemote &&
            formId == other.formId)
            return true
        return false
    }
}