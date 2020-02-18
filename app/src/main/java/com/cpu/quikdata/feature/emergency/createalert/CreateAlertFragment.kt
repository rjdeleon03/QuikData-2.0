package com.cpu.quikdata.feature.emergency.createalert


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.network.request.Notification
import com.cpu.quikdata.network.request.SendEmergencyAlertRequest
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_alert.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CreateAlertFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModel: CreateAlertViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.sendAlertResult.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })

        emergencySendButton.setOnClickListener {
            val request = SendEmergencyAlertRequest(Notification("hello", "goodbye"),
                "high",
                "/topics/emergency")
            mViewModel.sendAlert(request)
        }
    }

}
