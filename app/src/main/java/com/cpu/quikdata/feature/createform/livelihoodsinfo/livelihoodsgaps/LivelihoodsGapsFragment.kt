package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import kotlinx.android.synthetic.main.fragment_livelihoods_gaps.*

class LivelihoodsGapsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LivelihoodsGapsFragment()
    }

    private lateinit var mViewModel: LivelihoodsGapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_gaps, container, false)
    }

    override fun onDestroyView() {
        val livelihoodsGaps = LivelihoodsGaps(
            hasLocalMarket = livelihoodsGapsLocalMarketText.value,
            hasLocalMarketRemarks = livelihoodsGapsLocalMarketText.text,
            hasCashOpportunities = livelihoodsGapsCashOpportunitiesText.value,
            hasCashOpportunitiesRemarks = livelihoodsGapsCashOpportunitiesText.text,
            hasCredit = livelihoodsGapsCreditText.value,
            hasCreditRemarks = livelihoodsGapsCreditText.text,
            hasLivelihoodMaterials = livelihoodsGapsMaterialsText.value,
            hasLivelihoodMaterialsRemarks = livelihoodsGapsMaterialsText.text
        )
        mViewModel.updateLivelihoodsGaps(livelihoodsGaps)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProvider(this, mFactory).get(LivelihoodsGapsViewModel::class.java)
        mViewModel.livelihoodsGaps.observe(viewLifecycleOwner, Observer {
            livelihoodsGapsLocalMarketText.value = it.hasLocalMarket
            livelihoodsGapsLocalMarketText.text = it.hasLocalMarketRemarks
            livelihoodsGapsCashOpportunitiesText.value = it.hasCashOpportunities
            livelihoodsGapsCashOpportunitiesText.text = it.hasCashOpportunitiesRemarks
            livelihoodsGapsCreditText.value = it.hasCredit
            livelihoodsGapsCreditText.text = it.hasCreditRemarks
            livelihoodsGapsMaterialsText.value = it.hasLivelihoodMaterials
            livelihoodsGapsMaterialsText.text = it.hasLivelihoodMaterialsRemarks
        })
    }

}
