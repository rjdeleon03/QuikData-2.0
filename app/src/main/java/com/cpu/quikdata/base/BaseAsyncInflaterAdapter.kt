package com.cpu.quikdata.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAsyncInflaterAdapter<VH: RecyclerView.ViewHolder>(context: Context, layoutId: Int) :
    RecyclerView.Adapter<VH>() {

    companion object {
        const val MAX_CACHED_VIEWS = 3
    }

    private val mLayoutId = layoutId
    private val mAsyncLayoutInflater = AsyncLayoutInflater(context)
    private val mInflater = LayoutInflater.from(context)
    private val mCachedViews = Stack<View>()

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
            mCachedViews.pop().also { it.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ) }
        }
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view: View) : VH
}