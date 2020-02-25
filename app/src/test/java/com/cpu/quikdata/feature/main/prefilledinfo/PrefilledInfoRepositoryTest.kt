package com.cpu.quikdata.feature.main.prefilledinfo

import com.cpu.quikdata.common.CoroutineTestRule
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class PrefilledInfoRepositoryTest {

    private lateinit var mCoroutineScope: CoroutineScope
    private lateinit var mRepository: PrefilledInfoRepository
    private lateinit var mDatabase: AppDatabase
    private lateinit var mDao: PrefilledDataDao

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        createMockDatabase()
        mCoroutineScope = CoroutineScope(Dispatchers.IO)
        mRepository = PrefilledInfoRepository(mDatabase)
    }

    @After
    fun tearDown() {

        try {
            mCoroutineScope.cancel()
        } catch (_: Exception) {}
    }

    private fun createMockDatabase() {

        mDatabase = Mockito.mock(AppDatabase::class.java)
        mDao = Mockito.mock(PrefilledDataDao::class.java)

        Mockito.`when`(mDatabase.prefilledDataDao()).thenReturn(mDao)
    }

    @Test
    fun updatePrefilledData() {

        coroutinesTestRule.testDispatcher.runBlockingTest {
            mRepository.updatePrefilledData(PrefilledData())

            Mockito.verify(mDatabase, Mockito.times(1)).prefilledDataDao()
            Mockito.verify(mDao, Mockito.times(2)).get(anyString())
        }
    }


}