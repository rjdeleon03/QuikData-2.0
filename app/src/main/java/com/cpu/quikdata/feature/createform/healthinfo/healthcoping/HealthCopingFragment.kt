package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import kotlinx.android.synthetic.main.fragment_health_coping.*

class HealthCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HealthCopingFragment()
    }

    private val mViewModel: HealthCopingViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(HealthCopingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.healthInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_coping, container, false)
    }

    override fun onDestroyView() {
        val healthCoping = HealthCoping(
            copingStrategies = healthCopingStrategiesText.text
        )
        mViewModel.updateHealthCoping(healthCoping)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.healthCoping.observe(viewLifecycleOwner, Observer {
            healthCopingStrategiesText.text = it.copingStrategies
        })
    }

}
