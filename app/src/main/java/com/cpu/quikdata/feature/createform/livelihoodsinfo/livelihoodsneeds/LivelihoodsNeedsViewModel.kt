package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import javax.inject.Inject

class LivelihoodsNeedsViewModel @Inject constructor(
    private val mRepository: LivelihoodsNeedsRepository)
    : ViewModel() {

    val livelihoodsNeeds : LiveData<LivelihoodsNeeds>
        get() = mRepository.livelihoodsNeeds

    fun updateLivelihoodsNeeds(livelihoodsNeeds: LivelihoodsNeeds) =
        mRepository.updateData(livelihoodsNeeds)
}
