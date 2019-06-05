package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.customviews.CollapsibleContainer
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

abstract class BaseCollapsibleAdapter<R, VH: BaseCollapsibleAdapter.ViewHolder<R>>(context: Context, layoutId: Int, rowSaveListener: (R) -> Unit) :
    RecyclerView.Adapter<VH>() {

    private val mInflater = LayoutInflater.from(context)
    private val mLayoutId = layoutId
    private val mRowSaveListener = rowSaveListener
    protected var mRows: List<R>? = null
    protected var mExpandedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = mInflater.inflate(mLayoutId, parent, false)
        val holder = createViewHolder(view)
        holder.setOnClickListener {position ->
            notifyItemChanged(mExpandedItem)
            mExpandedItem = position
            notifyItemChanged(position)
        }
        return holder
    }

    abstract fun createViewHolder(view: View) : VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, position, mExpandedItem != position, mRowSaveListener)
    }

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    open fun setRows(rows: List<R>) {
        mRows = rows
        notifyDataSetChanged()
    }

    abstract class ViewHolder<R>(itemView: View, isDeletable: Boolean = false) : RecyclerView.ViewHolder(itemView) {

        private var mView = itemView

        init {
            collapsibleView?.isDeletable = isDeletable
        }

        val view: View
            get() = mView

        protected val collapsibleView: CollapsibleContainer?
            get() = mView as CollapsibleContainer

        fun setOnClickListener(l: (Int) -> Unit) {
            collapsibleView?.headerTextField?.clickWithGuard {
                l.invoke(view.tag as Int)
            }
        }

        fun setOnDeleteClickListener(l: (Int) -> Unit) {
            collapsibleView?.deleteButton?.clickWithGuard {
                l.invoke(view.tag as Int)
            }
        }

        fun populateWithData(row: R,
                             idx: Int,
                             isCollapsed: Boolean = true,
                             rowSaveListener: (R) -> Unit) {

            populateWithDataInternal(row, idx, isCollapsed, rowSaveListener)
            collapsibleView?.isCollapsed = isCollapsed
        }

        protected abstract fun populateWithDataInternal(row: R,
                                                        idx: Int,
                                                        isCollapsed: Boolean = true,
                                                        rowSaveListener: (R) -> Unit)
    }
}