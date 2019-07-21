package com.cpu.quikdata.feature.main.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.feature.consortium.ConsortiumActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeNewDncaFormButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.newInstance(context!!, formId, basicMode = true)
        }

        homeAboutButton.clickWithGuard {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        homeConsortiumButton.clickWithGuard {
            ConsortiumActivity.newInstance(context!!)
        }
    }

}
