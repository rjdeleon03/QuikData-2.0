package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds
import kotlinx.android.synthetic.main.fragment_livelihoods_needs.*

class LivelihoodsNeedsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LivelihoodsNeedsFragment()
    }

    private val mViewModel: LivelihoodsNeedsViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(LivelihoodsNeedsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.livelihoodsInfoComponent().create().inject(this)
    }

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
