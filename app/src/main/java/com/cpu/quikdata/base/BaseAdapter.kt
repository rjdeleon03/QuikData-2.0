package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.customviews.CollapsibleContainer
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

abstract class BaseAdapter<R, VH: BaseAdapter.ViewHolder<R>>(context: Context, layoutId: Int) :
    RecyclerView.Adapter<VH>() {

    private val mInflater = LayoutInflater.from(context)
    private val mLayoutId = layoutId
    protected var mRows: List<R>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = mInflater.inflate(mLayoutId, parent, false)
        val holder = createViewHolder(view)
        holder.setOnClickListener {itemId ->
            onItemClick(itemId)
        }
        return holder
    }

    abstract fun createViewHolder(view: View) : VH

    abstract fun onItemClick(id: String)

    override fun onBindViewHolder(holder: VH, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!)
    }

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

        fun setOnClickListener(l: (String) -> Unit) {
            view.clickWithGuard {
                l.invoke(view.tag as String)
            }
        }

        abstract fun populateWithData(data: R)
    }
}