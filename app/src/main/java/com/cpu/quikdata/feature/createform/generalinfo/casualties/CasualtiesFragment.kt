package com.cpu.quikdata.feature.createform.generalinfo.casualties

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import kotlinx.android.synthetic.main.fragment_casualties.*
import javax.inject.Inject

class CasualtiesFragment : BaseCollapsibleCreateFormFragment<CasualtiesAdapter, CasualtiesAdapter.ViewHolder>() {

    companion object {
        fun newInstance() = CasualtiesFragment()
    }

    @Inject lateinit var mCasualtiesAdapterFactory: CasualtiesAdapter.Factory

    private val mViewModel: CasualtiesViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(CasualtiesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.generalInfoComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_casualties, container, false)
    }

    override fun setupAdapter(expandedItemIndex: Int): CasualtiesAdapter {
        val adapter = mCasualtiesAdapterFactory.create({
            mViewModel.updateRow(it)
        }, expandedItemIndex)
        casualtiesRecyclerView.adapter = adapter
        return adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.casualties.observe(viewLifecycleOwner, Observer {
            mAdapter.setRows(it)
        })
    }

}
