package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import com.cpu.quikdata.common.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_evacuation_age.*

class EvacuationAgeFragment : BaseCollapsibleCreateFormFragment<EvacuationAgeAdapter, EvacuationAgeAdapter.ViewHolder>() {

    companion object {
        private const val EVACUATION_ID_KEY = "EVACUATION_ID_KEY"

        @JvmStatic
        fun newInstance(evacuationId: String): EvacuationAgeFragment {
            val fragment = EvacuationAgeFragment()
            val bundle = Bundle()
            bundle.putString(EVACUATION_ID_KEY, evacuationId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mViewModel: EvacuationAgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evacuation_age, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): EvacuationAgeAdapter {
        val adapter = EvacuationAgeAdapter(requireContext(), {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        evacuationAgeRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val evacuationId = arguments!!.getString(EVACUATION_ID_KEY)!!
        val factory = ViewModelFactory(requireActivity().application, evacuationId)
        mViewModel = ViewModelProvider(this, factory).get(EvacuationAgeViewModel::class.java)
        mViewModel.evacuationAge.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
