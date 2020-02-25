package com.cpu.quikdata.feature.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseCoroutineLiveDataTest: BaseCoroutineTest() {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
}