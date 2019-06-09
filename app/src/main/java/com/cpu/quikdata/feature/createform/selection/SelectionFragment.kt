package com.cpu.quikdata.feature.createform.selection

import android.graphics.Rect
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.customviews.ItemSection
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = SelectionFragment()
    }

    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()

        selectionSaveButton.clickWithGuard { activity!!.finish() }
        val itemSectionTouchedHandler = { item: ItemSection ->
            gridSectionLayout.children.forEach {
                if (it is ItemSection && it != item) {
                    it.collapse()
                }
            }
        }

        selectionFormDetailsButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_formDetailsFragment) },
            { mParentViewModel.submitFormDetails() },
            { }, itemSectionTouchedHandler)
        selectionGenInfoButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) },
            { mParentViewModel.submitGeneralInformation() },
            { }, itemSectionTouchedHandler)
        selectionShelterButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_shelterInfoFragment) },
            { mParentViewModel.submitShelterInformation() },
            { }, itemSectionTouchedHandler)
        selectionFoodButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_foodSecurityInfoFragment) },
            { mParentViewModel.submitFoodSecurity() },
            { }, itemSectionTouchedHandler)
        selectionLivelihoodsButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_livelihoodsInfoFragment) },
            { mParentViewModel.submitLivelihoods() },
            { }, itemSectionTouchedHandler)
        selectionHealthButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_healthInfoFragment) },
            { mParentViewModel.submitHealthInformation() },
            { }, itemSectionTouchedHandler)
        selectionWaterButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_waterSanitationInfoFragment) },
            { mParentViewModel.submitWashInformation() },
            { }, itemSectionTouchedHandler)
        selectionEvacuationButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_evacuationInfoFragment) },
            { mParentViewModel.submitEvacuationInformation() },
            { }, itemSectionTouchedHandler)
        selectionCaseStoriesButton.setButtonListeners(
            { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) },
            { },
            { }, itemSectionTouchedHandler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mParentViewModel.form.observe(viewLifecycleOwner, Observer {  })
    }

}
