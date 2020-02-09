package com.cpu.quikdata.di.module

import androidx.room.Room
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.feature.QuikDataApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val application: QuikDataApp) {

    private val mDatabase: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideAppDatabase() = mDatabase

    @Singleton
    @Provides
    fun providePrefilledDataDao() = mDatabase.prefilledDataDao()

    @Singleton
    @Provides
    fun provideFormDao() = mDatabase.formDao()

    @Singleton
    @Provides
    fun provideFormDetailsDao() = mDatabase.formDetailsDao()

    @Singleton
    @Provides
    fun provideBaselineDataDao() = mDatabase.baselineDataDao()


    @Singleton
    @Provides
    fun provideCalamityInfoDao() = mDatabase.calamityInfoDao()


    @Singleton
    @Provides
    fun providePopulationRowDao() = mDatabase.populationRowDao()


    @Singleton
    @Provides
    fun provideFamiliesDao() = mDatabase.familiesDao()


    @Singleton
    @Provides
    fun provideVulnerableRowDao() = mDatabase.vulnerableRowDao()


    @Singleton
    @Provides
    fun provideCasualtiesRowDao() = mDatabase.casualtiesRowDao()


    @Singleton
    @Provides
    fun provideCauseOfDeathRowDao() = mDatabase.causeOfDeathRowDao()


    @Singleton
    @Provides
    fun provideInfrastructureDamageRowDao() = mDatabase.infrastructureDamageRowDao()


    @Singleton
    @Provides
    fun provideHouseDamageRowDao() = mDatabase.houseDamageRowDao()


    @Singleton
    @Provides
    fun provideShelterCopingDao() = mDatabase.shelterCopingDao()


    @Singleton
    @Provides
    fun provideShelterNeedsRowDao() = mDatabase.shelterNeedsRowDao()


    @Singleton
    @Provides
    fun provideShelterAssistanceRowDao() = mDatabase.shelterAssistanceRowDao()


    @Singleton
    @Provides
    fun provideShelterGapsDao() = mDatabase.shelterGapsDao()


    @Singleton
    @Provides
    fun provideFoodSecurityImpactDao() = mDatabase.foodSecurityImpactDao()


    @Singleton
    @Provides
    fun provideFoodSecurityCopingDao() = mDatabase.foodSecurityCopingDao()


    @Singleton
    @Provides
    fun provideFoodSecurityNeedsDao() = mDatabase.foodSecurityNeedsDao()


    @Singleton
    @Provides
    fun provideFoodSecurityAssistanceRowDao() = mDatabase.foodSecurityAssistanceRowDao()


    @Singleton
    @Provides
    fun provideFoodSecurityGapsDao() = mDatabase.foodSecurityGapsDao()


    @Singleton
    @Provides
    fun provideIncomeBeforeRowDao() = mDatabase.incomeBeforeRowDao()


    @Singleton
    @Provides
    fun provideIncomeAfterRowDao() = mDatabase.incomeAfterRowDao()


    @Singleton
    @Provides
    fun provideEstimatedDamageRowDao() = mDatabase.estimatedDamageRowDao()


    @Singleton
    @Provides
    fun provideEstimatedDamageTypeDao() = mDatabase.estimatedDamageTypeDao()


    @Singleton
    @Provides
    fun provideLivelihoodsCopingDao() = mDatabase.livelihoodsCopingDao()


    @Singleton
    @Provides
    fun provideLivelihoodsNeedsDao() = mDatabase.livelihoodsNeedsDao()


    @Singleton
    @Provides
    fun provideLivelihoodsAssistanceRowDao() = mDatabase.livelihoodsAssistanceRowDao()


    @Singleton
    @Provides
    fun provideLivelihoodsGapsDao() = mDatabase.livelihoodsGapsDao()


    @Singleton
    @Provides
    fun provideDiseasesRowDao() = mDatabase.diseasesRowDao()


    @Singleton
    @Provides
    fun provideSpecialNeedsRowDao() = mDatabase.specialNeedsRowDao()


    @Singleton
    @Provides
    fun providePsychosocialRowDao() = mDatabase.psychosocialRowDao()


    @Singleton
    @Provides
    fun provideHealthCopingDao() = mDatabase.healthCopingDao()


    @Singleton
    @Provides
    fun provideHealthAssistanceRowDao() = mDatabase.healthAssistanceRowDao()


    @Singleton
    @Provides
    fun provideHealthGapsDao() = mDatabase.healthGapsDao()


    @Singleton
    @Provides
    fun provideWashConditionsDao() = mDatabase.washConditionsDao()


    @Singleton
    @Provides
    fun provideWashCopingDao() = mDatabase.washCopingDao()


    @Singleton
    @Provides
    fun provideWashAssistanceRowDao() = mDatabase.washAssistanceRowDao()


    @Singleton
    @Provides
    fun provideWashGapsDao() = mDatabase.washGapsDao()


    @Singleton
    @Provides
    fun provideEvacuationItemDao() = mDatabase.evacuationItemDao()


    @Singleton
    @Provides
    fun provideSiteInfoDao() = mDatabase.siteInfoDao()


    @Singleton
    @Provides
    fun provideEvacuationAgeRowDao() = mDatabase.evacuationAgeRowDao()


    @Singleton
    @Provides
    fun provideEvacuationFacilitiesDao() = mDatabase.evacuationFacilitiesDao()


    @Singleton
    @Provides
    fun provideEvacuationWashDao() = mDatabase.evacuationWashDao()


    @Singleton
    @Provides
    fun provideEvacuationProtectionDao() = mDatabase.evacuationProtectionDao()


    @Singleton
    @Provides
    fun provideEvacuationCopingDao() = mDatabase.evacuationCopingDao()


    @Singleton
    @Provides
    fun provideCaseStoriesDao() = mDatabase.caseStoriesDao()


    @Singleton
    @Provides
    fun provideCaseStoriesImageItemDao() = mDatabase.caseStoriesImageItemDao()
}