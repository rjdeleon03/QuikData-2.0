package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.customviews.CollapsibleContainer

abstract class BaseAdapter<R, VH: RecyclerView.ViewHolder>(context: Context, rowSaveListener: (R) -> Unit) :
    RecyclerView.Adapter<VH>() {

    protected val mInflater = LayoutInflater.from(context)
    protected var mRows: List<R>? = null
    protected var mExpandedItem = 0
    protected val mRowSaveListener = rowSaveListener

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    open fun setRows(rows: List<R>) {
        mRows = rows
        notifyDataSetChanged()
    }

    abstract class ViewHolder<R>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mView = itemView

        val view: View
            get() = mView

        protected val collapsibleView: CollapsibleContainer?
            get() = mView as CollapsibleContainer

        fun populateWithData(row: R,
                             idx: Int,
                             isCollapsed: Boolean = true,
                             rowSaveListener: (R) -> Unit,
                             rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            populateWithData(row, isCollapsed, rowSaveListener, rowCollapsedStateChangedListener)

            // Setup collapse changed listener
            collapsibleView?.onCollapsedStateChangedListener = {
                rowCollapsedStateChangedListener.invoke(idx, it)
            }

            // Expand if it is the item to be expanded
            if (!isCollapsed) {
                collapsibleView?.expand(false)
            } else {
                collapsibleView?.collapse(false)
            }



        }

        protected abstract fun populateWithData(row: R,
                                                isCollapsed: Boolean = true,
                                                rowSaveListener: (R) -> Unit,
                                                rowCollapsedStateChangedListener: (Int, Boolean) -> Unit)
    }
}