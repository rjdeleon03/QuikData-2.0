package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import kotlinx.android.synthetic.main.fragment_evacuation_wash.*

class EvacuationWashFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationWashFragment {
            val fragment = EvacuationWashFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: EvacuationWashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_wash, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateEvacuationWash(EvacuationWash(
            foodPreparation = evacuationWashFoodPreparationText.value,
            foodPreparationRemarks = evacuationWashFoodPreparationText.text,
            waterSource = evacuationWashWaterSourceText.value,
            waterSourceRemarks = evacuationWashWaterSourceText.text,
            toiletBaths = evacuationWashToiletBathsText.value,
            toiletBathsRemarks = evacuationWashToiletBathsText.text,
            garbageDisposal = evacuationWashGarbageDisposalText.value,
            garbageDisposalRemarks = evacuationWashGarbageDisposalText.text,
            clinic = evacuationWashClinicText.value,
            clinicRemarks = evacuationWashClinicText.text,
            sick = evacuationWashSickText.value,
            sickRemarks = evacuationWashSickText.text,
            handWashing = evacuationWashHandWashingText.value,
            handWashingRemarks = evacuationWashHandWashingText.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(activity!!.application, evacuationId)
        mViewModel = ViewModelProviders.of(this, factory).get(EvacuationWashViewModel::class.java)
        mViewModel.evacuationWash.observe(viewLifecycleOwner, Observer {
            evacuationWashFoodPreparationText.value = it.foodPreparation
            evacuationWashFoodPreparationText.text = it.foodPreparationRemarks
            evacuationWashWaterSourceText.value = it.waterSource
            evacuationWashWaterSourceText.text = it.waterSourceRemarks
            evacuationWashToiletBathsText.value = it.toiletBaths
            evacuationWashToiletBathsText.text = it.toiletBathsRemarks
            evacuationWashGarbageDisposalText.value = it.garbageDisposal
            evacuationWashGarbageDisposalText.text = it.garbageDisposalRemarks
            evacuationWashClinicText.value = it.clinic
            evacuationWashClinicText.text = it.clinicRemarks
            evacuationWashSickText.value = it.sick
            evacuationWashSickText.text = it.sickRemarks
            evacuationWashHandWashingText.value = it.handWashing
            evacuationWashHandWashingText.text = it.handWashingRemarks
        })
    }

}
