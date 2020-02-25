package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.feature.base.BaseCoroutineLiveDataTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class PrefilledInfoRepositoryTest: BaseCoroutineLiveDataTest() {

    private lateinit var mRepository: PrefilledInfoRepository
    private lateinit var mData: PrefilledData

    @Mock
    private lateinit var mDatabase: AppDatabase

    @Mock
    private lateinit var mDao: PrefilledDataDao

    override fun setupResources() {
        mData = PrefilledData()
        mRepository = PrefilledInfoRepository(mDatabase)
        Mockito.`when`(mDatabase.prefilledDataDao()).thenReturn(mDao)
    }

    @Test
    fun prefilledData() {
        Mockito.`when`(mDao.get(anyString())).thenReturn(MutableLiveData<PrefilledData>(mData))
        mRepository.prefilledData.observeForever {
            assertEquals(it, mData)
        }
    }

    @Test
    fun updatePrefilledData() {
        testCoroutineRule.runBlockingTest {
            mRepository.updatePrefilledData(mData)

            Mockito.verify(mDatabase, Mockito.times(1)).prefilledDataDao()
            Mockito.verify(mDao, Mockito.times(1)).update(mData)
        }
    }


}