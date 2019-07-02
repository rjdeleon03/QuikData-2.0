package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

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
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import kotlinx.android.synthetic.main.fragment_evacuation_coping.*

class EvacuationCopingFragment : Fragment() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationCopingFragment {
            val fragment = EvacuationCopingFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: EvacuationCopingViewModel

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

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(activity!!.application, evacuationId)
        mViewModel = ViewModelProviders.of(this, factory).get(EvacuationCopingViewModel::class.java)
        mViewModel.evacuationCoping.observe(viewLifecycleOwner, Observer {
            evacuationCopingMechanismText.text = it.copingMechanism
        })
    }

}
