package com.cpu.quikdata.feature.createform.selection


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.work.WorkManager
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.feature.createform.base.BaseSubmissionFragment
import com.cpu.quikdata.utils.isInternetAvailableThenToast
import kotlinx.android.synthetic.main.fragment_basic_selection.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BasicSelectionFragment : BaseSubmissionFragment() {

    private lateinit var mNavController: NavController

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
        selectionSendSaveButton.clickWithGuard {
            if (isInternetAvailableThenToast(
                    view.context,
                    R.string.text_error_no_internet_save_only
                )
            ) {
                mSubmissionViewModel.submitFormData(
                    WorkManager.getInstance(requireContext()),
                    initSubmissionWorker(true)
                )
                return@clickWithGuard
            }
            mParentViewModel.saveChangesToFormOnly()
        }

        selectionFormDetailsButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_formDetailsAndBaselineFragment) }
        selectionGenInfoButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_generalInfoFragment) }
        selectionCaseStoriesButton.setButtonListeners { mNavController.navigate(R.id.action_selection_to_caseStoriesFragment) }
    }
}
