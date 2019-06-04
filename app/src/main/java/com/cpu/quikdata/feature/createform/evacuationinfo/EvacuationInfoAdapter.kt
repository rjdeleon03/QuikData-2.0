package com.cpu.quikdata.feature.createform.evacuationinfo

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.data.evacuation.EvacuationItem

class EvacuationInfoAdapter(context: Context, onClickListener: (String) -> Unit) :
    BaseAdapter<EvacuationItem, EvacuationInfoAdapter.ViewHolder>(context, R.layout.item_evacuation) {

    private val mOnClickListener = onClickListener

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    override fun onItemClick(id: String) {
        mOnClickListener.invoke(id)
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<EvacuationItem>(itemView) {

        override fun populateWithData(data: EvacuationItem) {
            view.tag = data.id
        }
    }
}