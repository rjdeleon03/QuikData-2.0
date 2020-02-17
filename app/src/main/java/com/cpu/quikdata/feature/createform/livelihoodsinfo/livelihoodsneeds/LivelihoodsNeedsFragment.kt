package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import kotlinx.android.synthetic.main.fragment_livelihoods_needs.*
import javax.inject.Inject

class LivelihoodsNeedsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun start() = LivelihoodsNeedsFragment()
    }

    @Inject
    lateinit var mViewModel: LivelihoodsNeedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_needs, container, false)
    }

    override fun onDestroyView() {
        val livelihoodsNeeds = LivelihoodsNeeds(
            assistanceFillGap = livelihoodsNeedsAssistanceFillGapText.text,
            resourcesNeeded = livelihoodsResourcesNeededText.text,
            familiesInAssistance = livelihoodsNeedsFamiliesInAssistanceText.text
        )
        mViewModel.updateLivelihoodsNeeds(livelihoodsNeeds)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.livelihoodsNeeds.observe(viewLifecycleOwner, Observer {
            livelihoodsNeedsAssistanceFillGapText.text = it.assistanceFillGap
            livelihoodsResourcesNeededText.text = it.resourcesNeeded
            livelihoodsNeedsFamiliesInAssistanceText.text = it.familiesInAssistance
        })
    }

}
