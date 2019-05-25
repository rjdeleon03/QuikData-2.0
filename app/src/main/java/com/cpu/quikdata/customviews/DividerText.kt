package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.view_divider_text.view.*

class DividerText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_divider_text, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DividerText)
        dividerTextField.text = attributes.getString(R.styleable.DividerText_dividerText)
        attributes.recycle()
    }
}