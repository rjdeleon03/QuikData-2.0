package com.cpu.quikdata.feature.createform.selection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = SelectionFragment()
    }

    private lateinit var mNavController: NavController
    private lateinit var mViewModel: SelectionViewModel

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
        selectionFormDetailsButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_formDetailsFragment) }
        selectionGenInfoButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionShelterButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_shelterInfoFragment) }
        selectionFoodButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_foodSecurityInfoFragment) }
        selectionLivelihoodsButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_livelihoodsInfoFragment) }
        selectionHealthButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_healthInfoFragment) }
        selectionWaterButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_waterSanitationInfoFragment) }
        selectionEvacuationButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_evacuationInfoFragment) }
        selectionCaseStoriesButton.clickWithGuard { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }

        selectionFormDetailsButton.setOnLongClickListener {
            mParentViewModel.submitFormDetails()
            true
        }
        selectionGenInfoButton.setOnLongClickListener {
            mParentViewModel.submitGeneralInformation()
            true
        }
        selectionShelterButton.setOnLongClickListener {
            mParentViewModel.submitShelterInformation()
            true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mParentViewModel.form.observe(viewLifecycleOwner, Observer {  })
        mViewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
    }

}
