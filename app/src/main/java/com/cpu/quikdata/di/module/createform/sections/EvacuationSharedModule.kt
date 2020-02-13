package com.cpu.quikdata.di.module.createform.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment.Companion.EVACUATION_ID_KEY
import dagger.Module
import dagger.Provides

@Module
abstract class EvacuationSharedModule {

    companion object {

        @Provides
        fun provideEvacuationArguments(fragment: Fragment): Bundle = fragment.arguments ?: Bundle.EMPTY

        @Provides
        fun provideEvacuationId(bundle: Bundle): String = bundle.getString(EVACUATION_ID_KEY, "")
    }
}