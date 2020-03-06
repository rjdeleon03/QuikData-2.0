package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_vulnerable.*
import javax.inject.Inject

class VulnerableFragment : BaseCollapsibleCreateFormFragment<VulnerableAdapter, VulnerableAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = VulnerableFragment()
    }

    @Inject lateinit var mVulnerableAdapterFactory: VulnerableAdapter.Factory

    private val mViewModel: VulnerableViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(VulnerableViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.generalInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vulnerable, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): VulnerableAdapter {
        val adapter = mVulnerableAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        vulnerableRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.vulnerable.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
