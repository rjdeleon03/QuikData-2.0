package com.cpu.quikdata.di.annotation

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FormIdQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EvacuationIdQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FormBundleQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EvacuationBundleQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContextFromActivity

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContextFromFragment