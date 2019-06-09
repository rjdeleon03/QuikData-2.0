package com.cpu.quikdata.base

import android.os.Bundle
import android.view.View

abstract class BaseCollapsibleCreateFormFragment<A, H> :
    BaseCreateFormFragment()

        where A : BaseCollapsibleAdapter<*, H>, H : BaseCollapsibleAdapter.ViewHolder<*> {

    protected lateinit var mAdapter: A
    private val mExpandedItemKey = "EXPANDED_ITEM_INDEX_KEY"

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(mExpandedItemKey, mAdapter.expandedItemIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var expandedItemIndex = 0
        if (savedInstanceState != null) {
            expandedItemIndex = savedInstanceState.getInt(mExpandedItemKey, 0)
        }
        mAdapter = setupAdapter(expandedItemIndex)
    }

    protected abstract fun setupAdapter(expandedItemIndex: Int) : A

}