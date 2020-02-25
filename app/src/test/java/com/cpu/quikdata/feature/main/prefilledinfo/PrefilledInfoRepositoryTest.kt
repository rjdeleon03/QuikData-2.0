package com.cpu.quikdata.feature.main.prefilledinfo

import com.cpu.quikdata.common.TestCoroutineRule
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PrefilledInfoRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mRepository: PrefilledInfoRepository

    @Mock
    private lateinit var mDatabase: AppDatabase

    @Mock
    private lateinit var mDao: PrefilledDataDao

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        setupMockDatabase()
        mRepository = PrefilledInfoRepository(mDatabase)
    }

    private fun setupMockDatabase() {

        Mockito.`when`(mDatabase.prefilledDataDao()).thenReturn(mDao)
    }

    @Test
    fun updatePrefilledData() {

        testCoroutineRule.runBlockingTest {
            val data = PrefilledData()
            mRepository.updatePrefilledData(data)

            Mockito.verify(mDatabase, Mockito.times(1)).prefilledDataDao()
            Mockito.verify(mDao, Mockito.times(2)).update(data)
        }
    }


}