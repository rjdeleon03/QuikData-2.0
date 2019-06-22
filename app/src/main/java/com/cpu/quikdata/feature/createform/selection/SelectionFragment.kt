package com.cpu.quikdata.feature.createform.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.customviews.ItemSection
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment : BaseCreateFormFragment() {

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
        return inflater.inflate(R.layout.fragment_selection, container, false)
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

        selectionSaveButton.clickWithGuard { activity!!.finish() }
        selectionFormDetailsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_formDetailsAndBaselineFragment) }
        selectionGenInfoButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionShelterButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_shelterInfoFragment) }
        selectionFoodButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_foodSecurityInfoFragment) }
        selectionLivelihoodsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_livelihoodsInfoFragment) }
        selectionHealthButton.setButtonListeners{ mNavController.navigate(R.id.action_selection_to_healthInfoFragment) }
        selectionWaterButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_waterSanitationInfoFragment) }
        selectionEvacuationButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_evacuationInfoFragment) }
        selectionCaseStoriesButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mParentViewModel.form.observe(viewLifecycleOwner, Observer {  })
    }

}
