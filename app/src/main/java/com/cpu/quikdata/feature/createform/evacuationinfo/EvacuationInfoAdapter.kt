package com.cpu.quikdata.feature.createform.evacuationinfo

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.toDateString
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import kotlinx.android.synthetic.main.item_evacuation.view.*

class EvacuationInfoAdapter(context: Context, onClickListener: (String) -> Unit) :
    BaseAdapter<EvacuationItemDetails, EvacuationInfoAdapter.ViewHolder>(context, R.layout.item_evacuation) {

    private val mOnClickListener = onClickListener

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    override fun onItemClick(id: String) {
        mOnClickListener.invoke(id)
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<EvacuationItemDetails>(itemView) {

        override fun populateWithData(data: EvacuationItemDetails) {
            val item = data.item!!
            val info = data.siteInfo!![0]
            view.tag = item.id

            if (info.name.isNotEmpty()) {
                view.evacuationItemNameText.text = info.name
            }
            if (info.location.isNotEmpty()) {
                view.evacuationItemLocationText.text = info.location
            }
            view.evacuationItemDateText.text = info.evacuationDate.toDateString()
        }
    }
}