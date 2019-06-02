package com.cpu.quikdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRowDao
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingDao
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsDao
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactDao
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsDao
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormDao
import com.cpu.quikdata.data.formdetails.FormDetails
import com.cpu.quikdata.data.formdetails.FormDetailsDao
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfoDao
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRowDao
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRowDao
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.data.generalinfo.families.FamiliesDao
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRowDao
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRowDao
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRowDao
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRowDao
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRow
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRowDao
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import com.cpu.quikdata.data.health.healthcoping.HealthCopingDao
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import com.cpu.quikdata.data.health.healthgaps.HealthGapsDao
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRowDao
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRowDao
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageRow
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageRowDao
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageType
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageTypeDao
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRowDao
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingDao
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsDao
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsDao
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRowDao
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRowDao
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCopingDao
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGapsDao
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRowDao
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRowDao
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditionsDao
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCopingDao
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGapsDao

@Database(entities = [
        PrefilledData::class,
        Form::class,
        FormDetails::class,
        CalamityInfo::class,
        PopulationRow::class,
        Families::class,
        VulnerableRow::class,
        CasualtiesRow::class,
        CauseOfDeathRow::class,
        InfrastructureDamageRow::class,
        HouseDamageRow::class,
        ShelterCoping::class,
        ShelterNeedsRow::class,
        ShelterAssistanceRow::class,
        ShelterGaps::class,
        FoodSecurityImpact::class,
        FoodSecurityCoping::class,
        FoodSecurityNeeds::class,
        FoodSecurityAssistanceRow::class,
        FoodSecurityGaps::class,
        EstimatedDamageRow::class,
        EstimatedDamageType::class,
        LivelihoodsCoping::class,
        LivelihoodsNeeds::class,
        LivelihoodsAssistanceRow::class,
        LivelihoodsGaps::class,
        DiseasesRow::class,
        SpecialNeedsRow::class,
        PsychosocialRow::class,
        HealthCoping::class,
        HealthAssistanceRow::class,
        HealthGaps::class,
        WashConditions::class,
        WashCoping::class,
        WashAssistanceRow::class,
        WashGaps::class
    ],
    exportSchema = false,
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun prefilledDataDao(): PrefilledDataDao
    abstract fun formDao(): FormDao
    abstract fun formDetailsDao(): FormDetailsDao
    abstract fun calamityInfoDao(): CalamityInfoDao
    abstract fun populationRowDao(): PopulationRowDao
    abstract fun familiesDao(): FamiliesDao
    abstract fun vulnerableRowDao(): VulnerableRowDao
    abstract fun casualtiesRowDao(): CasualtiesRowDao
    abstract fun causeOfDeathRowDao(): CauseOfDeathRowDao
    abstract fun infrastructureDamageRowDao(): InfrastructureDamageRowDao
    abstract fun houseDamageRowDao(): HouseDamageRowDao
    abstract fun shelterCopingDao(): ShelterCopingDao
    abstract fun shelterNeedsRowDao(): ShelterNeedsRowDao
    abstract fun shelterAssistanceRowDao(): ShelterAssistanceRowDao
    abstract fun shelterGapsDao(): ShelterGapsDao
    abstract fun foodSecurityImpactDao(): FoodSecurityImpactDao
    abstract fun foodSecurityCopingDao(): FoodSecurityCopingDao
    abstract fun foodSecurityNeedsDao(): FoodSecurityNeedsDao
    abstract fun foodSecurityAssistanceRowDao(): FoodSecurityAssistanceRowDao
    abstract fun foodSecurityGapsDao(): FoodSecurityGapsDao
    abstract fun estimatedDamageRowDao(): EstimatedDamageRowDao
    abstract fun estimatedDamageTypeDao(): EstimatedDamageTypeDao
    abstract fun livelihoodsCopingDao(): LivelihoodsCopingDao
    abstract fun livelihoodsNeedsDao(): LivelihoodsNeedsDao
    abstract fun livelihoodsAssistanceRowDao(): LivelihoodsAssistanceRowDao
    abstract fun livelihoodsGapsDao(): LivelihoodsGapsDao
    abstract fun diseasesRowDao(): DiseasesRowDao
    abstract fun specialNeedsRowDao(): SpecialNeedsRowDao
    abstract fun psychosocialRowDao(): PsychosocialRowDao
    abstract fun healthCopingDao(): HealthCopingDao
    abstract fun healthAssistanceRowDao(): HealthAssistanceRowDao
    abstract fun healthGapsDao(): HealthGapsDao
    abstract fun washConditionsDao(): WashConditionsDao
    abstract fun washCopingDao(): WashCopingDao
    abstract fun washAssistanceRowDao(): WashAssistanceRowDao
    abstract fun washGapsDao(): WashGapsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val DATABASE_NAME = "quikdata_database"

        fun get(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}