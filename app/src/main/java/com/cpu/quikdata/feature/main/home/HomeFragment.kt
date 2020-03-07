package com.cpu.quikdata.feature.main.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.feature.consortium.ConsortiumActivity
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mAppComponent.newFormsComponent().create().inject(this)
    }

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
            CreateFormActivity.newInstance(requireContext(), formId, basicMode = true)
        }

        homeAboutButton.clickWithGuard {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        homeConsortiumButton.clickWithGuard {
            ConsortiumActivity.newInstance(requireContext())
        }
    }

}
