package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.customviews.CollapsibleContainer
import kotlinx.android.synthetic.main.view_collapsible_container.view.*
import java.util.*

abstract class BaseCollapsibleAdapter<R, VH: BaseCollapsibleAdapter.ViewHolder<R>>(context: Context,
                                                                                   layoutId: Int,
                                                                                   rowSaveListener: (R) -> Unit,
                                                                                   expandedItem: Int) :
    RecyclerView.Adapter<VH>() {

    companion object {
        const val MAX_CACHED_VIEWS = 3
    }

    private val mInflater = LayoutInflater.from(context)
    private val mLayoutId = layoutId
    private val mRowSaveListener = rowSaveListener
    private val mAsyncLayoutInflater = AsyncLayoutInflater(context)
    private val mCachedViews = Stack<View>()

    protected var mRows: List<R>? = null
    protected var mExpandedItem = expandedItem

    val expandedItemIndex: Int
        get() = mExpandedItem

    init {
        // Create views asynchronously and add them to the stack
        for (i in 0 until MAX_CACHED_VIEWS) {
            mAsyncLayoutInflater.inflate(layoutId, null) { view, _, _ ->
                mCachedViews.push(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = if (mCachedViews.isEmpty()) {
            mInflater.inflate(mLayoutId, parent, false)
        } else {
            mCachedViews.pop().also { it.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT) }
        }
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

            UIJobScheduler.submitJob {
                populateWithDataInternal(row, idx, isCollapsed, rowSaveListener)
                collapsibleView?.isCollapsed = isCollapsed
            }
        }

        protected abstract fun populateWithDataInternal(row: R,
                                                        idx: Int,
                                                        isCollapsed: Boolean = true,
                                                        rowSaveListener: (R) -> Unit)
    }
}