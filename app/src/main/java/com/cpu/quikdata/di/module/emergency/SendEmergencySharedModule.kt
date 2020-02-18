package com.cpu.quikdata.di.module.emergency

import android.app.Activity
import com.cpu.quikdata.di.annotation.SendEmergencyActivityScope
import com.cpu.quikdata.feature.emergency.SendEmergencyActivity
import dagger.Binds
import dagger.Module

@Module
abstract class SendEmergencySharedModule {

    @Binds
    @SendEmergencyActivityScope
    abstract fun bindsSendEmergencyActivity(activity: SendEmergencyActivity): Activity

}