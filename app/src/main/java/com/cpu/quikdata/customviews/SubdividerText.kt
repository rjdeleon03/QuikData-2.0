package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.view_divider_text.view.*

class SubdividerText(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_divider_text, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.SubdividerText)
        dividerTextField.text = attributes.getString(R.styleable.SubdividerText_dividerText)
        attributes.recycle()
    }
}