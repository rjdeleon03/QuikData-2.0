package com.cpu.quikdata.data.base

import org.junit.Before
import org.junit.Test

abstract class BaseDataTest<T> {

    protected var data = initData()

    abstract fun initData(): T

    @Before
    fun setUp() {
        data = initData()
    }

    @Test
    abstract fun getters()
}