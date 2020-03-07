package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.feature.createform.evacuationinfo.base.BaseEvacuationItemFragment
import kotlinx.android.synthetic.main.fragment_evacuation_facilities.*

class EvacuationFacilitiesFragment : BaseEvacuationItemFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): EvacuationFacilitiesFragment {
            return EvacuationFacilitiesFragment()
        }
    }

    private val mViewModel: EvacuationFacilitiesViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(EvacuationFacilitiesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mEvacuationItemComponent.inject(this)
    }

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
