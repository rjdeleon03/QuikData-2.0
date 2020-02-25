package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.common.TestCoroutineRule
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.feature.base.BaseCoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PrefilledInfoRepositoryTest: BaseCoroutineTest() {

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
        assertEquals(mRepository.prefilledData.value, mData)
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