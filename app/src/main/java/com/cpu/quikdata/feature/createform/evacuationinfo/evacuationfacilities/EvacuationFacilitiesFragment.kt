package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import kotlinx.android.synthetic.main.fragment_evacuation_facilities.*

class EvacuationFacilitiesFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationFacilitiesFragment {
            val fragment = EvacuationFacilitiesFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: EvacuationFacilitiesViewModel

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

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(activity!!.application, evacuationId)
        mViewModel = ViewModelProviders.of(this, factory).get(EvacuationFacilitiesViewModel::class.java)
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
