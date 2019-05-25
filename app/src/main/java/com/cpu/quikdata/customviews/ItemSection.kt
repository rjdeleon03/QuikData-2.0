package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.view_item_section.view.*

class ItemSection(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_item_section, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSection)
        label.text = attributes.getString(R.styleable.ItemSection_text)
        attributes.recycle()
    }
}