package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.feature.createform.evacuationinfo.base.BaseEvacuationItemFragment
import kotlinx.android.synthetic.main.fragment_evacuation_protection.*

class EvacuationProtectionFragment : BaseEvacuationItemFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): EvacuationProtectionFragment {
            return EvacuationProtectionFragment()
        }
    }

    private val mViewModel: EvacuationProtectionViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(EvacuationProtectionViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mEvacuationItemComponent.inject(this)
    }

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

        mViewModel.evacuationProtection.observe(viewLifecycleOwner, Observer {
            evacuationProtectionUnaccompaniedChildrenText.value = it.unaccompaniedChildren
            evacuationProtectionUnaccompaniedChildrenText.text = it.unaccompaniedChildrenRemarks
            evacuationProtectionToiletLocksText.value = it.toiletLocks
            evacuationProtectionToiletLocksText.text = it.toiletLocksRemarks
            evacuationProtectionSegregatedToiletText.value = it.segregatedToilet
            evacuationProtectionSegregatedToiletText.text = it.segregatedToiletRemarks
            evacuationProtectionProperLightingText.value = it.properLighting
            evacuationProtectionProperLightingText.text = it.properLightingRemarks
            evacuationProtectionSecurityOfficersText.value = it.securityOfficers
            evacuationProtectionSecurityOfficersText.text = it.securityOfficersRemarks
            evacuationProtectionSleepingPartitionsText.value = it.sleepingPartitions
            evacuationProtectionSleepingPartitionsText.text = it.sleepingPartitionsRemarks
            evacuationProtectionChildFriendlyText.value = it.childFriendly
            evacuationProtectionChildFriendlyText.text = it.childFriendlyRemarks
            evacuationProtectionPregnantSpaceText.value = it.pregnantSpace
            evacuationProtectionPregnantSpaceText.text = it.pregnantSpaceRemarks
            evacuationProtectionDisabledSpaceText.value = it.disabledSpace
            evacuationProtectionDisabledSpaceText.text = it.disabledSpaceRemarks
            evacuationProtectionReligiousSpaceText.value = it.religiousSpace
            evacuationProtectionReligiousSpaceText.text = it.religiousSpaceRemarks
            evacuationProtectionGbvReferralText.value = it.gbvReferral
            evacuationProtectionGbvReferralText.text = it.gbvReferralRemarks
            evacuationProtectionGbvProtectionText.value = it.gbvProtection
            evacuationProtectionGbvProtectionText.text = it.gbvProtectionRemarks
            evacuationProtectionGbvFocalPointText.value = it.gbvFocal
            evacuationProtectionGbvFocalPointText.text = it.gbvFocalRemarks
        })
    }

}
