package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.customviews.CollapsibleContainer

abstract class BaseAssistanceAdapter<R, VH: RecyclerView.ViewHolder>(context: Context, rowSaveListener: (R) -> Unit) :
    BaseAdapter<R, VH>(context, rowSaveListener) {

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun setRows(rows: List<R>) {
        if (mRows != null) mExpandedItem = rows.size - 1
        super.setRows(rows)
    }
}