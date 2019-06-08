package com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "food_security_impact",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class FoodSecurityImpact(@PrimaryKey(autoGenerate = false)
                              val id: String = "",
                              var hasFoodAvailabilityProblem: Boolean = false,
                              var hasFoodAvailabilityProblemRemarks: String = "",
                              var lacksFoodAccess: Boolean = false,
                              var lacksFoodAccessRemarks: String = "",
                              var hasFoodAccessConstraints: Boolean = false,
                              var hasFoodAccessConstraintsRemarks: String = "",
                              var hasOtherFoodSources: Boolean = false,
                              var hasOtherFoodSourcesRemarks: String = "",
                              var eatingTimesBeforeEmergency: String = "",
                              var eatingTimesAfterEmergency: String = "",
                              var meetsFoodNeedsBeforeEmergency: String = "",
                              var meetsFoodNeedsAfterEmergency: String = "",
                              var foodProductionChange: String = "",
                              var nextFoodRation: String = "",
                              var formIdRemote: String = "",
                              val formId: String = "") {

    fun copyFrom(foodSecurityImpact: FoodSecurityImpact) {
        hasFoodAvailabilityProblem = foodSecurityImpact.hasFoodAvailabilityProblem
        hasFoodAvailabilityProblemRemarks = foodSecurityImpact.hasFoodAvailabilityProblemRemarks
        lacksFoodAccess = foodSecurityImpact.lacksFoodAccess
        lacksFoodAccessRemarks = foodSecurityImpact.lacksFoodAccessRemarks
        hasFoodAccessConstraints = foodSecurityImpact.hasFoodAccessConstraints
        hasFoodAccessConstraintsRemarks = foodSecurityImpact.hasFoodAccessConstraintsRemarks
        hasOtherFoodSources = foodSecurityImpact.hasOtherFoodSources
        hasOtherFoodSourcesRemarks = foodSecurityImpact.hasOtherFoodSourcesRemarks
        eatingTimesBeforeEmergency = foodSecurityImpact.eatingTimesBeforeEmergency
        eatingTimesAfterEmergency = foodSecurityImpact.eatingTimesAfterEmergency
        meetsFoodNeedsBeforeEmergency = foodSecurityImpact.meetsFoodNeedsBeforeEmergency
        meetsFoodNeedsAfterEmergency = foodSecurityImpact.meetsFoodNeedsAfterEmergency
        foodProductionChange = foodSecurityImpact.foodProductionChange
        nextFoodRation = foodSecurityImpact.nextFoodRation
        formIdRemote = foodSecurityImpact.formIdRemote
    }
}