package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_families.*

class FamiliesFragment : BaseCreateFormFragment() {

    companion object {
        fun newInstance() = FamiliesFragment()
    }

    private lateinit var mViewModel: FamiliesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_families, container, false)
    }

    override fun onDestroyView() {
        val families = Families(
            affectedFamilies = familiesAffectedText.number,
            affectedHouseholds = householdsAffectedText.number,
            displacedFamilies = familiesDisplacedText.number,
            displacedHouseholds = householdsDisplacedText.number
        )
        mViewModel.updateFamilies(families)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(FamiliesViewModel::class.java)
        mViewModel.families.observe(viewLifecycleOwner, Observer {
            UIJobScheduler.submitJob {
                familiesAffectedText.number = it.affectedFamilies
                householdsAffectedText.number = it.affectedHouseholds
                familiesDisplacedText.number = it.displacedFamilies
                householdsDisplacedText.number = it.displacedHouseholds
            }
        })
    }

}
