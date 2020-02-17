package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import kotlinx.android.synthetic.main.fragment_livelihoods_coping.*
import javax.inject.Inject

class LivelihoodsCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun start() = LivelihoodsCopingFragment()
    }

    @Inject
    lateinit var mViewModel: LivelihoodsCopingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livelihoods_coping, container, false)
    }

    override fun onDestroyView() {
        val livelihoodsCoping = LivelihoodsCoping(
            copingStrategies = livelihoodsCopingStrategiesText.text,
            newIncome = livelihoodsCopingNewIncomeText.text,
            livelihoodSkills = livelihoodsCopingLivelihoodSkillsText.text
        )
        mViewModel.updateLivelihoodsCoping(livelihoodsCoping)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.livelihoodsCoping.observe(viewLifecycleOwner, Observer {
            livelihoodsCopingStrategiesText.text = it.copingStrategies
            livelihoodsCopingNewIncomeText.text = it.newIncome
            livelihoodsCopingLivelihoodSkillsText.text = it.livelihoodSkills
        })
    }

}
