package com.cpu.quikdata.data.base

import org.junit.Test

abstract class BaseEqualityOnlyTest<T>: BaseDataTest<T>() {

    @Test
    abstract fun equals()

    @Test
    abstract fun notEquals()
}