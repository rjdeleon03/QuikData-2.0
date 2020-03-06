package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import kotlinx.android.synthetic.main.fragment_wash_coping.*

class WashCopingFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = WashCopingFragment()
    }

    private val mViewModel: WashCopingViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(WashCopingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.waterSanitationInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wash_coping, container, false)
    }

    override fun onDestroyView() {
        val washCoping = WashCoping(
            copingStrategies = washCopingStrategiesText.text
        )
        mViewModel.updateWashCoping(washCoping)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.washCoping.observe(viewLifecycleOwner, Observer {
            washCopingStrategiesText.text = it.copingStrategies
        })
    }

}
