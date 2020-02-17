package com.cpu.quikdata.feature.main.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.feature.consortium.ConsortiumActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.emergency.SendEmergencyActivity
import com.cpu.quikdata.utils.generateId
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeNewDncaFormButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.start(context!!, formId, basicMode = true)
        }

        homeEmergencyAlertButton.clickWithGuard {
            SendEmergencyActivity.start(context!!)
        }

        homeAboutButton.clickWithGuard {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        homeConsortiumButton.clickWithGuard {
            ConsortiumActivity.start(context!!)
        }
    }

}
