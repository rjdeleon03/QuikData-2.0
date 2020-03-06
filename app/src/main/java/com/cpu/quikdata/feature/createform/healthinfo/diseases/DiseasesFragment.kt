package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_diseases.*
import javax.inject.Inject

class DiseasesFragment : BaseCollapsibleCreateFormFragment<DiseasesAdapter, DiseasesAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        fun newInstance() = DiseasesFragment()
    }

    @Inject lateinit var mDiseasesAdapterFactory: DiseasesAdapter.Factory

    private val mViewModel: DiseasesViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(DiseasesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.healthInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diseases, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): DiseasesAdapter {
        val adapter = mDiseasesAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        diseasesRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.diseases.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
