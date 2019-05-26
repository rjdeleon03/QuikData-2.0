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

class CollapsibleContainer(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var mIsCollapsed = false

    init {
        View.inflate(context, R.layout.view_collapsible_container, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CollapsibleContainer)
        headerTextField.text = attributes.getString(com.cpu.quikdata.R.styleable.CollapsibleContainer_headerText)
        attributes.recycle()

        headerTextField.clickWithGuard {
            mIsCollapsed = if (contentLayout.visibility == View.GONE) {
                expand()
                false
            } else {
                collapse()
                true
            }
        }
    }

    val isCollapsed: Boolean
        get() = mIsCollapsed

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (contentLayout == null) {
            super.addView(child, index, params)
        } else {
            contentLayout.addView(child, index, params)
        }
    }

    // region Collapse/expand based on https://stackoverflow.com/questions/4946295/android-expand-collapse-animation

    fun collapse() {

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

    fun expand() {
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