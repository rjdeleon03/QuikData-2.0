package com.cpu.quikdata.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.utils.runOnMainThread

abstract class BaseAssistanceAdapter<R, VH: BaseCollapsibleAdapter.ViewHolder<R>>(context: Context,
                                                                                  rowSaveListener: (R) -> Unit,
                                                                                  deleteClickListener: (R) -> Unit,
                                                                                  layoutId: Int = com.cpu.quikdata.R.layout.item_assistance,
                                                                                  expandedItem: Int) :
    BaseCollapsibleAdapter<R, VH>(context, layoutId, rowSaveListener, expandedItem) {

    private val mDeleteClickListener = deleteClickListener
    private var mDeletedItemTag = DeletedItem.SUCCEEDING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = super.onCreateViewHolder(parent, viewType)
        holder.setOnDeleteClickListener {
            mDeleteClickListener.invoke(mRows!![holder.adapterPosition])
            if (holder.adapterPosition == mExpandedItem) {
                mDeletedItemTag = DeletedItem.EXPANDED
            } else if (holder.adapterPosition < mExpandedItem) {
                mDeletedItemTag = DeletedItem.PRECEDING
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun setRows(rows: List<R>) {
        // Move focus to last-added item if:
        // - a new item is added
        // - expanded or preceding item is already deleted
        if (mRows != null ) {
            if (mRows!!.size < rows.size ||
                (mRows!!.size > rows.size && mDeletedItemTag != DeletedItem.SUCCEEDING)) {
                when (mDeletedItemTag) {
                    DeletedItem.PRECEDING -> mExpandedItem -= 1
                    else -> mExpandedItem = rows.size - 1
                }
                mDeletedItemTag = DeletedItem.SUCCEEDING
                runOnMainThread { notifyItemChanged(mExpandedItem) }
            }
        }
        super.setRows(rows)

    }

    abstract class ViewHolder<R>(itemView: View) : BaseCollapsibleAdapter.ViewHolder<R>(itemView, true)

    private enum class DeletedItem {
        EXPANDED,
        PRECEDING,
        SUCCEEDING
    }
}