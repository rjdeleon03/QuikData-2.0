package com.cpu.quikdata.di.module.emergency.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FirebaseMessagingApiQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.emergency.createalert.CreateAlertRepository
import com.cpu.quikdata.feature.emergency.createalert.CreateAlertViewModel
import com.cpu.quikdata.network.FirebaseMessagingApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CreateAlertModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateAlertViewModel::class)
    abstract fun bindsCreateAlertViewModel(createAlertViewModel: CreateAlertViewModel): ViewModel

    companion object {

        @Provides
        fun provideCreateAlertRepository(
            @FirebaseMessagingApiQualifier firebaseMessagingApi: FirebaseMessagingApi)
                : CreateAlertRepository {
            return CreateAlertRepository(firebaseMessagingApi)
        }
    }

}