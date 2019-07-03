package com.cpu.quikdata.feature.createform.evacuationinfo

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAsyncInflaterAdapter
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.toDateString
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import kotlinx.android.synthetic.main.item_evacuation.view.*

class EvacuationInfoAdapter(context: Context, onClickListener: (String) -> Unit, onDeleteListener: (EvacuationItemDetails) -> Unit) :
    BaseAsyncInflaterAdapter<EvacuationInfoAdapter.ViewHolder>(context, R.layout.item_evacuation) {

    private val mOnClickListener = onClickListener
    private val mOnDeleteListener = onDeleteListener
    private var mRows: List<EvacuationItemDetails>? = null

    override fun createViewHolder(view: View): ViewHolder {
        val holder = ViewHolder(view) { mOnDeleteListener.invoke(it) }
        holder.setOnClickListener { itemId ->
            mOnClickListener.invoke(itemId)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!)
    }

    fun setRows(rows: List<EvacuationItemDetails>) {
        mRows = rows
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onDeleteListener: (EvacuationItemDetails) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private var mView = itemView
        private var mOnDeleteListener = onDeleteListener

        val view: View
            get() = mView

        fun setOnClickListener(l: (String) -> Unit) {
            view.clickWithGuard {
                l.invoke(view.tag as String)
            }
        }

        fun populateWithData(data: EvacuationItemDetails) {
            val item = data.item!!
            val info = data.siteInfo!![0]
            view.tag = item.id


            if (info.name.isNotEmpty()) {
                view.evacuationItemNameText.text = info.name
            }

            if (info.location.isNotEmpty()) {
                view.evacuationItemLocationText.text = info.location
            }

            view.evacuationDeleteButton.clickWithGuard {
                mOnDeleteListener.invoke(data)
            }
            view.evacuationItemDateText.text = info.evacuationDate.toDateString()
        }
    }
}