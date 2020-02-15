package com.cpu.quikdata.feature.createform.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.customviews.ItemSection
import com.cpu.quikdata.feature.createform.BaseSubmissionFragment
import kotlinx.android.synthetic.main.fragment_complete_selection.*

class SelectionFragment : BaseSubmissionFragment() {

    companion object {
        private const val EXPANDED_ITEM_KEY = "EXPANDED_ITEM_KEY"

        @JvmStatic
        fun newInstance() = SelectionFragment()
    }

    private lateinit var mNavController: NavController
    private var mExpandedItemId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_complete_selection, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(EXPANDED_ITEM_KEY, mExpandedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()

        setupClipping(selectionRootLayout)

        if (savedInstanceState != null) {
            mExpandedItemId = savedInstanceState.getInt(EXPANDED_ITEM_KEY, 0)
            view.findViewById<ItemSection>(mExpandedItemId)?.expand(false)
        }

        selectionSendSaveButton.clickWithGuard {
            showProgressDialog()
            mParentViewModel.saveFormAsActual()
        }
        selectionFormDetailsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_formDetailsAndBaselineFragment) }
        selectionGenInfoButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionCaseStoriesButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }

        /* Optional sections */
        selectionShelterButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_shelterInfoFragment) })
        selectionFoodButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_foodSecurityInfoFragment) })
        selectionLivelihoodsButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_livelihoodsInfoFragment) })
        selectionHealthButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_healthInfoFragment) })
        selectionWaterButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_waterSanitationInfoFragment) })
        selectionEvacuationButton.setButtonListeners ({ mNavController.navigate(R.id.action_selection_to_evacuationInfoFragment) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel.form.observe(viewLifecycleOwner) {
            selectionShelterButton.isIncluded = it.includeShelter
            selectionFoodButton.isIncluded = it.includeFood
            selectionLivelihoodsButton.isIncluded = it.includeLivelihoods
            selectionHealthButton.isIncluded = it.includeHealth
            selectionWaterButton.isIncluded = it.includeWash
            selectionEvacuationButton.isIncluded = it.includeEvacuation
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mParentViewModel.toggleSectionInclusions(
            selectionShelterButton.isIncluded,
            selectionFoodButton.isIncluded,
            selectionLivelihoodsButton.isIncluded,
            selectionHealthButton.isIncluded,
            selectionWaterButton.isIncluded,
            selectionEvacuationButton.isIncluded
        )
    }

}
