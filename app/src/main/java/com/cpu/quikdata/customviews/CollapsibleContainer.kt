package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class CollapsibleContainer(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_collapsible_container, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CollapsibleContainer)
        headerTextField.text = attributes.getString(R.styleable.CollapsibleContainer_headerText)
        attributes.recycle()

        headerTextField.clickWithGuard {
            if (contentLayout.visibility == View.GONE) {
                contentLayout.visibility = View.VISIBLE
            } else {
                contentLayout.visibility = View.GONE
            }
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (contentLayout == null) {
            super.addView(child, index, params)
        } else {
            contentLayout.addView(child, index, params)
        }
    }
}