package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import kotlinx.android.synthetic.main.fragment_shelter_coping.*

class ShelterCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ShelterCopingFragment()
    }

    private lateinit var mViewModel: ShelterCopingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_coping, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateShelterCoping(ShelterCoping(
            displacedFamiliesLocation = shelterCopingDisplacedFamiliesLocationText.text,
            howToGetHomesBack = shelterCopingHowToGetHomesBackText.text,
            whenToReturnHome = shelterCopingWhenToReturnHomeText.text,
            ifCannotReturnHome = shelterCopingIfCannotReturnHomeText.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(ShelterCopingViewModel::class.java)
        mViewModel.shelterCoping.observe(viewLifecycleOwner, Observer {
            shelterCopingDisplacedFamiliesLocationText.text = it.displacedFamiliesLocation
            shelterCopingHowToGetHomesBackText.text = it.howToGetHomesBack
            shelterCopingWhenToReturnHomeText.text = it.whenToReturnHome
            shelterCopingIfCannotReturnHomeText.text = it.ifCannotReturnHome
        })
    }

}
