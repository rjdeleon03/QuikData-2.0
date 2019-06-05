package com.cpu.quikdata.base

import android.content.Context
import android.view.View

abstract class BaseAssistanceAdapter<R, VH: BaseCollapsibleAdapter.ViewHolder<R>>(context: Context, rowSaveListener: (R) -> Unit) :
    BaseCollapsibleAdapter<R, VH>(context, com.cpu.quikdata.R.layout.item_assistance, rowSaveListener) {

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun setRows(rows: List<R>) {
        if (mRows != null) mExpandedItem = rows.size - 1
        super.setRows(rows)
    }

    abstract class ViewHolder<R>(itemView: View) : BaseCollapsibleAdapter.ViewHolder<R>(itemView, true)
}