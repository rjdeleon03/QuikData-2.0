package com.cpu.quikdata.feature.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cpu.quikdata.common.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
abstract class BaseCoroutineTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setupResources()
    }

    abstract fun setupResources()
}