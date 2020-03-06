package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import kotlinx.android.synthetic.main.fragment_livelihoods_coping.*

class LivelihoodsCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LivelihoodsCopingFragment()
    }

    private val mViewModel: LivelihoodsCopingViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(LivelihoodsCopingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.livelihoodsInfoComponent().create().inject(this)
    }

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
