package com.cpu.quikdata.data.generalinfo.populationrow

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "population_row",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class PopulationRow(@PrimaryKey(autoGenerate = false)
                         override val id: String = "",
                         val type: Int = 0,
                         var affectedMale: Int = 0,
                         var affectedFemale: Int = 0,
                         var displacedMale: Int = 0,
                         var displacedFemale: Int = 0,
                         val formId: String = "") : BaseDataWithId {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is PopulationRow) return false
        if (this === other) return true
        if (id == other.id &&
            type == other.type &&
            affectedMale == other.affectedMale &&
            affectedFemale == other.affectedFemale &&
            displacedMale == other.displacedMale &&
            displacedFemale == other.displacedFemale &&
            formId == other.formId)
            return true
        return false
    }
}