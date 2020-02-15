package com.cpu.quikdata.data.base

import org.junit.Test

abstract class BaseCopyOnlyTest<T>: BaseDataTest<T>() {

    @Test
    abstract fun copyFrom()
}