package com.cpu.quikdata.data.generalinfo.families

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "families",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class Families(@PrimaryKey(autoGenerate = false)
                    val id: String = "",
                    var affectedFamilies: Int = 0,
                    var affectedHouseholds: Int = 0,
                    var displacedFamilies: Int = 0,
                    var displacedHouseholds: Int = 0,
                    var formIdRemote: String = "",
                    val formId: String = "") {

    fun copyFrom(families: Families) {
        affectedFamilies = families.affectedFamilies
        affectedHouseholds = families.affectedHouseholds
        displacedFamilies = families.displacedFamilies
        displacedHouseholds = families.displacedHouseholds
        formIdRemote = families.formIdRemote
    }
}