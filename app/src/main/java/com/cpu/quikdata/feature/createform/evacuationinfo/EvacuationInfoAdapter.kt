package com.cpu.quikdata.feature.createform.evacuationinfo

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.toDateString
import com.cpu.quikdata.data.evacuation.EvacuationItem
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import kotlinx.android.synthetic.main.item_evacuation.view.*
import kotlinx.android.synthetic.main.view_divider_text.view.*

class EvacuationInfoAdapter(context: Context, onClickListener: (String) -> Unit, onDeleteListener: (EvacuationItemDetails) -> Unit) :
    BaseAdapter<EvacuationItemDetails, EvacuationInfoAdapter.ViewHolder>(context, R.layout.item_evacuation) {

    private val mOnClickListener = onClickListener
    private val mOnDeleteListener = onDeleteListener

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view) { mOnDeleteListener.invoke(it) }

    override fun onItemClick(id: String) {
        mOnClickListener.invoke(id)
    }

    class ViewHolder(itemView: View, onDeleteListener: (EvacuationItemDetails) -> Unit) :
        BaseAdapter.ViewHolder<EvacuationItemDetails>(itemView) {

        private var mOnDeleteListener = onDeleteListener

        override fun populateWithData(data: EvacuationItemDetails) {
            val item = data.item!!
            val info = data.siteInfo!![0]
            view.tag = item.id

            UIJobScheduler.submitJob {
                if (info.name.isNotEmpty()) {
                    view.evacuationItemNameText.text = info.name
                }
            }
            UIJobScheduler.submitJob {
                if (info.location.isNotEmpty()) {
                    view.evacuationItemLocationText.text = info.location
                }
            }
            UIJobScheduler.submitJob {
                view.evacuationDeleteButton.clickWithGuard {
                    mOnDeleteListener.invoke(data)
                }
                view.evacuationItemDateText.text = info.evacuationDate.toDateString()
            }
        }
    }
}