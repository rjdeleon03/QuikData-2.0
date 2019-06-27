package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import kotlinx.android.synthetic.main.fragment_evacuation_protection.*

class EvacuationProtectionFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationProtectionFragment {
            val fragment = EvacuationProtectionFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: EvacuationProtectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_protection, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateEvacuationProtection(EvacuationProtection(
            unaccompaniedChildren = evacuationProtectionUnaccompaniedChildrenText.value,
            unaccompaniedChildrenRemarks = evacuationProtectionUnaccompaniedChildrenText.text,
            toiletLocks = evacuationProtectionToiletLocksText.value,
            toiletLocksRemarks = evacuationProtectionToiletLocksText.text,
            segregatedToilet = evacuationProtectionSegregatedToiletText.value,
            segregatedToiletRemarks = evacuationProtectionSegregatedToiletText.text,
            properLighting = evacuationProtectionProperLightingText.value,
            properLightingRemarks = evacuationProtectionProperLightingText.text,
            securityOfficers = evacuationProtectionSecurityOfficersText.value,
            securityOfficersRemarks = evacuationProtectionSecurityOfficersText.text,
            sleepingPartitions = evacuationProtectionSleepingPartitionsText.value,
            sleepingPartitionsRemarks = evacuationProtectionSleepingPartitionsText.text,
            childFriendly = evacuationProtectionChildFriendlyText.value,
            childFriendlyRemarks = evacuationProtectionChildFriendlyText.text,
            pregnantSpace = evacuationProtectionPregnantSpaceText.value,
            pregnantSpaceRemarks = evacuationProtectionPregnantSpaceText.text,
            disabledSpace = evacuationProtectionDisabledSpaceText.value,
            disabledSpaceRemarks = evacuationProtectionDisabledSpaceText.text,
            religiousSpace = evacuationProtectionReligiousSpaceText.value,
            religiousSpaceRemarks = evacuationProtectionReligiousSpaceText.text,
            gbvReferral = evacuationProtectionGbvReferralText.value,
            gbvReferralRemarks = evacuationProtectionGbvReferralText.text,
            gbvProtection = evacuationProtectionGbvProtectionText.value,
            gbvProtectionRemarks = evacuationProtectionGbvProtectionText.text,
            gbvFocal = evacuationProtectionGbvFocalPointText.value,
            gbvFocalRemarks = evacuationProtectionGbvFocalPointText.text
        )
        )
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(activity!!.application, evacuationId)
        mViewModel = ViewModelProviders.of(this, factory).get(EvacuationProtectionViewModel::class.java)
        mViewModel.evacuationProtection.observe(viewLifecycleOwner, Observer {
            UIJobScheduler.submitJob { evacuationProtectionUnaccompaniedChildrenText.value = it.unaccompaniedChildren }
            UIJobScheduler.submitJob { evacuationProtectionUnaccompaniedChildrenText.text = it.unaccompaniedChildrenRemarks }
            UIJobScheduler.submitJob { evacuationProtectionToiletLocksText.value = it.toiletLocks }
            UIJobScheduler.submitJob { evacuationProtectionToiletLocksText.text = it.toiletLocksRemarks }
            UIJobScheduler.submitJob { evacuationProtectionSegregatedToiletText.value = it.segregatedToilet }
            UIJobScheduler.submitJob { evacuationProtectionSegregatedToiletText.text = it.segregatedToiletRemarks }
            UIJobScheduler.submitJob { evacuationProtectionProperLightingText.value = it.properLighting }
            UIJobScheduler.submitJob { evacuationProtectionProperLightingText.text = it.properLightingRemarks }
            UIJobScheduler.submitJob { evacuationProtectionSecurityOfficersText.value = it.securityOfficers }
            UIJobScheduler.submitJob { evacuationProtectionSecurityOfficersText.text = it.securityOfficersRemarks }
            UIJobScheduler.submitJob { evacuationProtectionSleepingPartitionsText.value = it.sleepingPartitions }
            UIJobScheduler.submitJob { evacuationProtectionSleepingPartitionsText.text = it.sleepingPartitionsRemarks }
            UIJobScheduler.submitJob { evacuationProtectionChildFriendlyText.value = it.childFriendly }
            UIJobScheduler.submitJob { evacuationProtectionChildFriendlyText.text = it.childFriendlyRemarks }
            UIJobScheduler.submitJob { evacuationProtectionPregnantSpaceText.value = it.pregnantSpace }
            UIJobScheduler.submitJob { evacuationProtectionPregnantSpaceText.text = it.pregnantSpaceRemarks }
            UIJobScheduler.submitJob { evacuationProtectionDisabledSpaceText.value = it.disabledSpace }
            UIJobScheduler.submitJob { evacuationProtectionDisabledSpaceText.text = it.disabledSpaceRemarks }
            UIJobScheduler.submitJob { evacuationProtectionReligiousSpaceText.value = it.religiousSpace }
            UIJobScheduler.submitJob { evacuationProtectionReligiousSpaceText.text = it.religiousSpaceRemarks }
            UIJobScheduler.submitJob { evacuationProtectionGbvReferralText.value = it.gbvReferral }
            UIJobScheduler.submitJob { evacuationProtectionGbvReferralText.text = it.gbvReferralRemarks }
            UIJobScheduler.submitJob { evacuationProtectionGbvProtectionText.value = it.gbvProtection }
            UIJobScheduler.submitJob { evacuationProtectionGbvProtectionText.text = it.gbvProtectionRemarks }
            UIJobScheduler.submitJob { evacuationProtectionGbvFocalPointText.value = it.gbvFocal }
            UIJobScheduler.submitJob { evacuationProtectionGbvFocalPointText.text = it.gbvFocalRemarks }
        })
    }

}
