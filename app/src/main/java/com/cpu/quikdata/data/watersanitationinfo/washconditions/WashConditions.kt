package com.cpu.quikdata.data.watersanitationinfo.washconditions

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "wash_conditions",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class WashConditions(@PrimaryKey(autoGenerate = false)
                          val id: String = "",
                          var drinkingWaterLevel: Int = 0,
                          var drinkingWaterRemarks: String = "",
                          var bathingWaterLevel: Int = 0,
                          var bathingWaterRemarks: String = "",
                          var numberOfWaterPoints: String = "",
                          var waterPotable: String = "",
                          var fetchingWaterTime: String = "",
                          var waterSourceOwner: String = "",
                          var waterCost: String = "",
                          var transportationCost: String = "",
                          var timesOfNoWater: String = "",
                          var handWashingFacilities: String = "",
                          var garbageDisposalFacilities: String = "",
                          var wasteSegregation: String = "",
                          var menstruationManagement: String = "",
                          var napkinsDisposal: String = "",
                          var diapersDisposal: String = "",
                          var defecationPractices: String = "",
                          var toiletFacilities: String = "",
                          var toiletConditions: String = "",
                          var defecationThreat: String = "",
                          var existingFacilities: String = "",
                          var securityIssues: String = "",
                          var toiletSegregation: String = "",
                          var toiletAccessibility: String = "",
                          var formIdRemote: String = "",
                          val formId: String = "") {

    fun copyFrom(washConditions: WashConditions) {
        drinkingWaterLevel = washConditions.drinkingWaterLevel
        drinkingWaterRemarks = washConditions.drinkingWaterRemarks
        bathingWaterLevel = washConditions.bathingWaterLevel
        bathingWaterRemarks = washConditions.bathingWaterRemarks
        numberOfWaterPoints = washConditions.numberOfWaterPoints
        waterPotable = washConditions.waterPotable
        fetchingWaterTime = washConditions.fetchingWaterTime
        waterSourceOwner = washConditions.waterSourceOwner
        waterCost = washConditions.waterCost
        transportationCost = washConditions.transportationCost
        timesOfNoWater = washConditions.timesOfNoWater
        handWashingFacilities = washConditions.handWashingFacilities
        garbageDisposalFacilities = washConditions.garbageDisposalFacilities
        wasteSegregation = washConditions.wasteSegregation
        menstruationManagement = washConditions.menstruationManagement
        napkinsDisposal = washConditions.napkinsDisposal
        diapersDisposal = washConditions.diapersDisposal
        defecationPractices = washConditions.defecationPractices
        toiletFacilities = washConditions.toiletFacilities
        toiletConditions = washConditions.toiletConditions
        defecationThreat = washConditions.defecationThreat
        existingFacilities = washConditions.existingFacilities
        securityIssues = washConditions.securityIssues
        toiletSegregation = washConditions.toiletSegregation
        toiletAccessibility = washConditions.toiletAccessibility
        formIdRemote = washConditions.formIdRemote
    }
}