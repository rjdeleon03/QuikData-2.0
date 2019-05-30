package com.cpu.quikdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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
        InfrastructureDamageRow::class
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