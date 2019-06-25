package com.cpu.quikdata.data.livelihoodsinfo.incomeafter

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "income_after_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class IncomeAfterRow(@PrimaryKey(autoGenerate = false)
                          val id: String = "",
                          var source: String = "",
                          var isPrimary: Boolean = true,
                          var households: Int = 0,
                          var male: Int = 0,
                          var female: Int = 0,
                          var boys: Int = 0,
                          var girls: Int = 0,
                          var income: Int = 0,
                          val dateCreated: Long = 0L,
                          val formId: String = "") {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is IncomeAfterRow) return false
        if (this === other) return true
        if (id == other.id &&
            source == other.source &&
            isPrimary == other.isPrimary &&
            households == other.households &&
            male == other.male &&
            female == other.female &&
            boys == other.boys &&
            girls == other.girls &&
            income == other.income &&
            dateCreated == other.dateCreated &&
            formId == other.formId)
            return true
        return false
    }
}