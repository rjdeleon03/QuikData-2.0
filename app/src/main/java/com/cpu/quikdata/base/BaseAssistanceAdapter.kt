package com.cpu.quikdata.base

import android.content.Context
import android.view.View
import android.view.ViewGroup

abstract class BaseAssistanceAdapter<R, VH: BaseCollapsibleAdapter.ViewHolder<R>>(context: Context,
                                                                                  rowSaveListener: (R) -> Unit,
                                                                                  deleteClickListener: (R) -> Unit,
                                                                                  layoutId: Int = com.cpu.quikdata.R.layout.item_assistance,
                                                                                  expandedItem: Int) :
    BaseCollapsibleAdapter<R, VH>(context, layoutId, rowSaveListener, expandedItem) {

    private val mDeleteClickListener = deleteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = super.onCreateViewHolder(parent, viewType)
        holder.setOnDeleteClickListener { mDeleteClickListener.invoke(mRows!![it]) }
        return holder
    }

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun setRows(rows: List<R>) {
        if (mRows != null) mExpandedItem = rows.size - 1
        super.setRows(rows)
    }

    abstract class ViewHolder<R>(itemView: View) : BaseCollapsibleAdapter.ViewHolder<R>(itemView, true)
}