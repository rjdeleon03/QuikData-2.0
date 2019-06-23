package com.cpu.quikdata.feature.createform.basicselection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.observeOnly
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.feature.main.MainActivity
import kotlinx.android.synthetic.main.fragment_basic_selection.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BasicSelectionFragment : BaseCreateFormFragment() {

    private lateinit var mNavController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = findNavController()

        setupClipping(basicSelectionRootLayout)
        selectionArchiveButton.clickWithGuard {
            mParentViewModel.deleteForm()
            MainActivity.newInstance(context!!)
            activity!!.finish()
        }
        selectionSendSaveButton.clickWithGuard {
            mParentViewModel.saveFormAsActual()
            MainActivity.newInstance(context!!)
            activity!!.finish()
        }
        selectionFormDetailsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_formDetailsAndBaselineFragment) }
        selectionGenInfoButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionCaseStoriesButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel.form.observeOnly(viewLifecycleOwner)
    }
}