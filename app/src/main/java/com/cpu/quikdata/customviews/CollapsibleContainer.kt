package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.view_collapsible_container.view.*
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.core.content.ContextCompat

@Suppress("unused", "MemberVisibilityCanBePrivate")
class CollapsibleContainer(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var mIsUpdateFinished = false
    private var mIsCollapsed = false
    private var mIsDeletable = false
    private var mOnDetachedListener: (() -> Unit)? = null
    private var mOnCollapsedStateChangedListener: ((Boolean) -> Unit)? = null

    var isCollapsed: Boolean
        get() = mIsCollapsed
        set(value) {
            mIsCollapsed = value
            collapseOrExpand(mIsCollapsed)
        }

    var isDeletable: Boolean
        get() = mIsDeletable
        set(value) {
            mIsDeletable = value
            toggleDeleteButtonVisibility(mIsDeletable)
        }

    var onDetachedListener: (() -> Unit)? = null
        set(value) {
            field = value
            mOnDetachedListener = field
        }

    var onCollapsedStateChangedListener: ((Boolean) -> Unit)? = null
        set(value) {
            field = value
            mOnCollapsedStateChangedListener = field
        }

    init {
        View.inflate(context, R.layout.view_collapsible_container, this)

        // Set other layout properties
        orientation = LinearLayout.VERTICAL
        background = context.getDrawable(android.R.color.white)

        // Retrieve attributes then apply
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CollapsibleContainer)
        headerTextField.text = attributes.getString(R.styleable.CollapsibleContainer_headerText)
        val collapsedFlag = attributes.getBoolean(R.styleable.CollapsibleContainer_collapsed, true)
        val deletableFlag = attributes.getBoolean(R.styleable.CollapsibleContainer_deletable, false)
        attributes.recycle()

        // Collapse if collapsed flag is true
        isCollapsed = collapsedFlag

        // Hide delete button if deletable flag is false
        isDeletable = !deletableFlag
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mIsUpdateFinished = false
        collapseOrExpand(mIsCollapsed)
    }

    override fun onDetachedFromWindow() {
        if (!mIsUpdateFinished) {
            mOnDetachedListener?.invoke()
            mIsUpdateFinished = true
        }
        super.onDetachedFromWindow()
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (contentLayout == null) {
            super.addView(child, index, params)
        } else {
            contentLayout.addView(child, index, params)
        }
    }

    fun collapse() {
        contentLayout.visibility = View.GONE
        headerTextField.background = ContextCompat.getDrawable(context, R.drawable.ripple_accent)
        mainLayout.background = ContextCompat.getDrawable(context, R.color.lightGray)
    }

    fun expand() {
        contentLayout.visibility = View.VISIBLE
        headerTextField.background = ContextCompat.getDrawable(context, R.color.colorAccentLight)
        mainLayout.background = ContextCompat.getDrawable(context, R.color.colorAccentLight)
    }

    private fun collapseOrExpand(willCollapse: Boolean) {
        if (willCollapse) {
            collapse()
        } else {
            expand()
        }
    }

    private fun toggleDeleteButtonVisibility(isDeletable: Boolean) {
        if (isDeletable) {
            deleteButton.visibility = View.VISIBLE
        } else {
            deleteButton.visibility = View.GONE
        }
    }
}