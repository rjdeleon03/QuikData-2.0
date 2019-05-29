package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<R, VH: RecyclerView.ViewHolder>(context: Context, rowSaveListener: (R) -> Unit) :
    RecyclerView.Adapter<VH>() {

    protected val mInflater = LayoutInflater.from(context)
    protected var mRows: List<R>? = null

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    fun setRows(rows: List<R>) {
        mRows = rows
        notifyDataSetChanged()
    }

    abstract class ViewHolder<R>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mView = itemView

        val view: View
            get() = mView

        abstract fun populateWithData(row: R, rowSaveListener: (R) -> Unit)
    }
}