package com.cpu.quikdata.di.annotation

import dagger.MapKey

@MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class MainActivityScope()