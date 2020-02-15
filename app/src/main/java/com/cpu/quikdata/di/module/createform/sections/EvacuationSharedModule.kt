package com.cpu.quikdata.di.module.createform.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cpu.quikdata.di.annotation.EvacuationBundleQualifier
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment.Companion.EVACUATION_ID_KEY
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class EvacuationSharedModule {

    companion object {

        @Provides
        @EvacuationBundleQualifier
        fun provideEvacuationArguments(fragment: Fragment): Bundle = fragment.arguments ?: Bundle.EMPTY

        @Provides
        @EvacuationIdQualifier
        fun provideEvacuationId(@EvacuationBundleQualifier bundle: Bundle): String =
            bundle.getString(EVACUATION_ID_KEY, "")
    }
}