package com.cpu.quikdata.di.app.module

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Module
abstract class WorkerModule {

}

interface ChildWorkerFactory {
    fun create(appContext: Context, workerParams: WorkerParameters): ListenableWorker
}

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)