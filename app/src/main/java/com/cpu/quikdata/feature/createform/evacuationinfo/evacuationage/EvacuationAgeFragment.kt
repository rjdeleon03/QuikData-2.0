package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment.Companion.EVACUATION_ID_KEY
import kotlinx.android.synthetic.main.fragment_evacuation_age.*
import javax.inject.Inject

class EvacuationAgeFragment : BaseCollapsibleCreateFormFragment<EvacuationAgeAdapter, EvacuationAgeAdapter.ViewHolder>() {

    companion object {

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationAgeFragment {
            val fragment = EvacuationAgeFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var mViewModel: EvacuationAgeViewModel

    @Inject
    lateinit var mAdapterFactory: EvacuationAgeAdapter.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_age, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): EvacuationAgeAdapter {
        val adapter = mAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        evacuationAgeRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.evacuationAge.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
