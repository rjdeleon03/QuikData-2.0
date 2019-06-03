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

@Suppress("unused", "MemberVisibilityCanBePrivate")
class CollapsibleContainer(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var mIsUpdateFinished = false
    private var mIsCollapsed = false
    private var mOnDetachedListener: (() -> Unit)? = null
    private var mOnCollapsedStateChangedListener: ((Boolean) -> Unit)? = null

    var isCollapsed: Boolean
        get() = mIsCollapsed
        set(value) {
            mIsCollapsed = value
//            mOnCollapsedStateChangedListener?.invoke(mIsCollapsed)
            post {
                if (mIsCollapsed) {
                    contentLayout.visibility = View.GONE
                } else {
                    contentLayout.visibility = View.VISIBLE
                }
            }
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
        val collapsedFlag = attributes.getBoolean(R.styleable.CollapsibleContainer_collapsed, false)
        attributes.recycle()

        // Collapse if collapsed flag is true
        if (collapsedFlag) {
            isCollapsed = true
            contentLayout.visibility = View.GONE
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mIsUpdateFinished = false
        if (mIsCollapsed) {
            contentLayout.visibility = View.GONE
        } else {
            contentLayout.visibility = View.VISIBLE
        }
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

    // region Collapse/expand based on https://stackoverflow.com/questions/4946295/android-expand-collapse-animation

    fun collapse(isAnimated: Boolean = true) {
        if (mIsCollapsed) return
        isCollapsed = true

        if (!isAnimated) {
            contentLayout.visibility = View.GONE
            return
        }

        val initialHeight = contentLayout.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    contentLayout.visibility = View.GONE
                } else {
                    contentLayout.layoutParams.height = (initialHeight - (initialHeight * interpolatedTime)).toInt()
                    contentLayout.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = 300
        contentLayout.startAnimation(a)
    }

    fun expand(isAnimated: Boolean = true) {
        if (!mIsCollapsed) return
        isCollapsed = false

        if (!isAnimated) {
            contentLayout.visibility = View.VISIBLE
            return
        }

        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((contentLayout.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        contentLayout.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = contentLayout.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        contentLayout.layoutParams.height = 1
        contentLayout.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                contentLayout.layoutParams.height = if (interpolatedTime == 1f)
                    LinearLayout.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                contentLayout.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = 300
        contentLayout.startAnimation(a)
    }

    // endregion
}