package com.cpu.quikdata.data.shelterinfo.sheltercoping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "shelter_coping",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class ShelterCoping(@PrimaryKey(autoGenerate = false)
                             val id: String = "",
                         var displacedFamiliesLocation: String = "",
                         var howToGetHomesBack: String = "",
                         var whenToReturnHome: String = "",
                         var ifCannotReturnHome: String = "",
                         val formId: String = "") {

    fun copyFrom(shelterCoping: ShelterCoping) {
        displacedFamiliesLocation = shelterCoping.displacedFamiliesLocation
        howToGetHomesBack = shelterCoping.howToGetHomesBack
        whenToReturnHome = shelterCoping.whenToReturnHome
        ifCannotReturnHome = shelterCoping.ifCannotReturnHome
    }
}