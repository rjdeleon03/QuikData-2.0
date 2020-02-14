package com.cpu.quikdata.di.module.common

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.cpu.quikdata.di.annotation.ContextFromActivity
import com.cpu.quikdata.di.annotation.ContextFromFragment
import dagger.Module
import dagger.Provides

@Module
abstract class ContextModule {

    companion object {

        @Provides
        @ContextFromActivity
        fun provideContextFromActivity(activity: Activity): Context {
            return activity
        }

        @Provides
        @ContextFromFragment
        fun provideContextFromFragment(fragment: Fragment): Context {
            return fragment.context!!
        }
    }
}