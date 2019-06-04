package com.cpu.quikdata.feature.createform.evacuationinfo

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.data.evacuation.EvacuationItem

class EvacuationInfoAdapter(context: Context) : BaseAdapter<EvacuationItem, EvacuationInfoAdapter.ViewHolder>(context, R.layout.item_evacuation) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    override fun onItemClick(item: EvacuationItem) {
        // TODO: Open evacuation item activity, pass item ID
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<EvacuationItem>(itemView) {

        private val mView = itemView

        override fun populateWithData(data: EvacuationItem) {

        }
    }
}