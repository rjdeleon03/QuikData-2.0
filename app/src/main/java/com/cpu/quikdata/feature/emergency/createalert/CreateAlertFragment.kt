package com.cpu.quikdata.feature.emergency.createalert


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.GenericMessageDialogFragment
import com.cpu.quikdata.dialog.ProgressDialogFragment
import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_alert.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CreateAlertFragment : DaggerFragment() {

    companion object {
        private const val MAX_EMERGENCY_CHAR_COUNT = 255
    }

    @Inject
    lateinit var mViewModel: CreateAlertViewModel

    private var mDialog: DialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (childFragmentManager.findFragmentByTag(ProgressDialogFragment.TAG) as? ProgressDialogFragment)?.let {
            mDialog = it
        }

        setupViewModel()
        setupSendButton()
    }

    private fun setupSendButton() {
        emergencySendButton.setOnClickListener {
            if (!isEmergencyTextValid()) {
                showInvalidInputDialog()
                return@setOnClickListener
            }
            val request =
                SendEmergencyAlertRequest.createWithTextOnly(emergencyInformationText.text)
            mViewModel.sendAlert(request)
        }
    }

    private fun setupViewModel() {
        mViewModel.sendAlertResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> showSuccessDialog()
                false -> showFailureDialog()
            }
        })

        mViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgressDialog()
                return@Observer
            }
            mDialog?.dismiss()
        })
    }

    private fun isEmergencyTextValid(): Boolean {
        return !emergencyInformationText.text.isNullOrBlank()
                && emergencyInformationText.text.length <= MAX_EMERGENCY_CHAR_COUNT
    }

    private fun showProgressDialog() {
        mDialog?.dismiss()
        mDialog = ProgressDialogFragment.start(textId = R.layout.dialog_progress_send_emergency, cancelable = false)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
    }

    private fun showSuccessDialog() {
        mDialog?.dismiss()
        mDialog = GenericMessageDialogFragment.start(textId = R.string.send_emergency_success).apply {
            setOnOkClickListener { activity?.apply { finish() } }
        }
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
    }

    private fun showFailureDialog() {
        mDialog?.dismiss()
        mDialog = GenericMessageDialogFragment.start(textId = R.string.send_emergency_failure)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
    }

    private fun showInvalidInputDialog() {
        mDialog?.dismiss()
        mDialog = GenericMessageDialogFragment.start(textId = R.string.send_emergency_invalid_input)
        mDialog?.show(childFragmentManager, ProgressDialogFragment.TAG)
    }

}
