package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import kotlinx.android.synthetic.main.fragment_health_gaps.*

class HealthGapsFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HealthGapsFragment()
    }

    private lateinit var mViewModel: HealthGapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health_gaps, container, false)
    }

    override fun onDestroyView() {
        val healthGaps = HealthGaps(
            nearestHospital = healthGapsNearestHospitalText.text,
            servicesAvailable = healthGapsServicesAvailableText.text,
            servicesAccessible = healthGapsServicesAccessibleText.text,
            reproductiveHealth = healthGapsReproductiveHealthText.text
        )
        mViewModel.updateHealthGaps(healthGaps)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(HealthGapsViewModel::class.java)
        mViewModel.healthGaps.observe(viewLifecycleOwner, Observer {
            healthGapsNearestHospitalText.text = it.nearestHospital
            healthGapsServicesAvailableText.text = it.servicesAvailable
            healthGapsServicesAccessibleText.text = it.servicesAccessible
            healthGapsReproductiveHealthText.text = it.reproductiveHealth
        })
    }

}
