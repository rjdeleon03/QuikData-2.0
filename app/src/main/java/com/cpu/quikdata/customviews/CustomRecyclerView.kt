package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.view_custom_recycler_view.view.*

class CustomRecyclerView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_custom_recycler_view, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView)
        emptyRecyclerViewText.text = attributes.getString(R.styleable.CustomRecyclerView_text)
        attributes.recycle()
    }

    fun updateDisplayBasedOnItemCount(itemCount: Int) {
        if (itemCount == 0) {
            recyclerView.visibility = View.GONE
            emptyRecyclerViewText.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyRecyclerViewText.visibility = View.GONE
        }
    }
}