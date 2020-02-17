package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment.Companion.EVACUATION_ID_KEY
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_evacuation_facilities.*
import javax.inject.Inject

class EvacuationFacilitiesFragment : DaggerFragment() {

    companion object {

        @JvmStatic
        fun start(evacuationId: String): EvacuationFacilitiesFragment {
            val fragment = EvacuationFacilitiesFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var mViewModel: EvacuationFacilitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_facilities, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateEvacuationFacilities(EvacuationFacilities(
            capacity = evacuationFacilitiesCapacityText.number,
            toilet = evacuationFacilitiesToiletsText.value,
            toiletRemarks = evacuationFacilitiesToiletsText.text,
            wideEntrance = evacuationFacilitiesWideEntranceText.value,
            wideEntranceRemarks = evacuationFacilitiesWideEntranceText.text,
            electricity = evacuationFacilitiesElectricityText.value,
            electricityRemarks = evacuationFacilitiesElectricityText.text,
            waterSupply = evacuationFacilitiesWaterSupplyText.value,
            waterSupplyRemarks = evacuationFacilitiesWaterSupplyText.text,
            ventilation = evacuationFacilitiesVentilationText.value,
            ventilationRemarks = evacuationFacilitiesVentilationText.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.evacuationFacilities.observe(viewLifecycleOwner, Observer {
            evacuationFacilitiesCapacityText.number = it.capacity
            evacuationFacilitiesToiletsText.value = it.toilet
            evacuationFacilitiesToiletsText.text = it.toiletRemarks
            evacuationFacilitiesWideEntranceText.value = it.wideEntrance
            evacuationFacilitiesWideEntranceText.text = it.wideEntranceRemarks
            evacuationFacilitiesElectricityText.value = it.electricity
            evacuationFacilitiesElectricityText.text = it.electricityRemarks
            evacuationFacilitiesWaterSupplyText.value = it.waterSupply
            evacuationFacilitiesWaterSupplyText.text = it.waterSupplyRemarks
            evacuationFacilitiesVentilationText.value = it.ventilation
            evacuationFacilitiesVentilationText.text = it.ventilationRemarks
        })
    }

}
