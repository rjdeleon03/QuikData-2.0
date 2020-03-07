package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.feature.createform.evacuationinfo.base.BaseEvacuationItemFragment
import kotlinx.android.synthetic.main.fragment_evacuation_coping.*

class EvacuationCopingFragment : BaseEvacuationItemFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): EvacuationCopingFragment {
            return EvacuationCopingFragment()
        }
    }

    private val mViewModel: EvacuationCopingViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(EvacuationCopingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mEvacuationItemComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_coping, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateEvacuationCoping(EvacuationCoping(
            copingMechanism = evacuationCopingMechanismText.text
        ))
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.evacuationCoping.observe(viewLifecycleOwner, Observer {
            evacuationCopingMechanismText.text = it.copingMechanism
        })
    }

}
