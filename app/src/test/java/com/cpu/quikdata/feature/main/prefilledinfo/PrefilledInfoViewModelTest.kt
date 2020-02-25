package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.common.TestContextProvider
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.feature.base.BaseCoroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.Rule

@ExperimentalCoroutinesApi
class PrefilledInfoViewModelTest: BaseCoroutineTest() {

    private lateinit var mViewModel: PrefilledInfoViewModel
    private lateinit var mData: PrefilledData

    @Mock
    private lateinit var mRepository: PrefilledInfoRepository

    override fun setupResources() {
        mData = PrefilledData()
        mViewModel = PrefilledInfoViewModel(mRepository, TestContextProvider())
    }

    @Test
    fun getPrefilledData() {
        Mockito.`when`(mRepository.prefilledData).thenReturn(MutableLiveData<PrefilledData>(mData))
        assertEquals(mViewModel.prefilledData.value, mData)
    }

    @Test
    fun updatePrefilledData() {
        mViewModel.prefilledData.observeForever {
            println("Hello")
        }
        testCoroutineRule.runBlockingTest {
            mViewModel.updatePrefilledData(mData)
            Mockito.verify(mRepository, Mockito.times(1)).updatePrefilledData(mData)
        }
    }
}