package com.cpu.quikdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingDao
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
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRowDao
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCopingDao
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGapsDao
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRowDao

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
        ShelterGaps::class,
        FoodSecurityImpact::class,
        FoodSecurityCoping::class,
        FoodSecurityNeeds::class
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
    abstract fun shelterGapsDao(): ShelterGapsDao
    abstract fun foodSecurityImpactDao(): FoodSecurityImpactDao
    abstract fun foodSecurityCopingDao(): FoodSecurityCopingDao
    abstract fun foodSecurityNeedsDao(): FoodSecurityNeedsDao

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