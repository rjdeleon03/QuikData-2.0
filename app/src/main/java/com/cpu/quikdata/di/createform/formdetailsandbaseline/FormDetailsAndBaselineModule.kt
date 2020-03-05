package com.cpu.quikdata.di.createform.formdetailsandbaseline

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataViewModel
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FormDetailsAndBaselineModule {

    @Binds
    @IntoMap
    @ViewModelKey(FormDetailsViewModel::class)
    abstract fun bindFormDetailsViewModel(formDetailsViewModel: FormDetailsViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(BaselineDataViewModel::class)
//    abstract fun bindBaselineDataViewModel(baselineDataViewModel: BaselineDataViewModel): ViewModel
}