package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_shelter_needs.*
import javax.inject.Inject

class ShelterNeedsFragment : BaseCollapsibleCreateFormFragment<ShelterNeedsAdapter, ShelterNeedsAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = ShelterNeedsFragment()
    }

    @Inject
    lateinit var mViewModel: ShelterNeedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelter_needs, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): ShelterNeedsAdapter {
        val adapter = ShelterNeedsAdapter(context!!, {
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        shelterNeedsRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.shelterNeeds.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
