package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.view_item_section.view.*

class ItemSection(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mIsCollapsed = true
    private var mMainButtonListener: (() -> Unit)? = null
    private var mTopButtonListener: (() -> Unit)? = null
    private var mBottomButtonListener: (() -> Unit)? = null
    private var mTouchListener: ((ItemSection) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_item_section, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSection)
        mainButton.text = attributes.getString(R.styleable.ItemSection_text)
        optionsLabel.text = attributes.getString(R.styleable.ItemSection_text)
        attributes.recycle()

        /*
        mainButton.setOnLongClickListener {
            expand()
            true
        }
        */
        mainButton.clickWithGuard {
            mMainButtonListener?.invoke()
        }
        topButton.clickWithGuard {
            mTopButtonListener?.invoke()
        }
        bottomButton.clickWithGuard {
            collapse()
            mBottomButtonListener?.invoke()
        }
    }

    /*
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // Collapse when another item is clicked
        if (!mIsCollapsed && ev != null && ev.action == MotionEvent.ACTION_UP) {
            mTouchListener?.invoke(this)
        }
        return super.onInterceptTouchEvent(ev)
    }
    */

    fun setButtonListeners(main: () -> Unit) {
        mMainButtonListener = main
    }

    fun setButtonListeners(main: () -> Unit,
                           top: () -> Unit,
                           bottom: () -> Unit,
                           touch: (ItemSection) -> Unit) {
        mMainButtonListener = main
        mTopButtonListener = top
        mBottomButtonListener = bottom
        mTouchListener = touch
    }

    fun expand(isAnimated: Boolean = true) {
        if (!mIsCollapsed) return
        mIsCollapsed = false

        if (!isAnimated) {
            optionsLayout.visibility = View.VISIBLE
            return
        }

        val matchParentMeasureSpec =
            MeasureSpec.makeMeasureSpec((optionsLayout.parent as View).width, MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        optionsLayout.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = (optionsLayout.parent as View).height

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        optionsLayout.layoutParams.height = 1
        optionsLayout.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                optionsLayout.layoutParams.height = if (interpolatedTime == 1f)
                    LayoutParams.MATCH_PARENT
                else
                    (targetHeight * interpolatedTime).toInt()
                optionsLayout.requestLayout()
            }
        }
        a.duration = 200
        optionsLayout.startAnimation(a)
    }

    fun collapse(isAnimated: Boolean = true) {
        if (mIsCollapsed) return
        mIsCollapsed = true

        if (!isAnimated) {
            optionsLayout.visibility = View.GONE
            return
        }

        val initialHeight = optionsLayout.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    optionsLayout.visibility = View.GONE
                } else {
                    optionsLayout.layoutParams.height = (initialHeight - (initialHeight * interpolatedTime)).toInt()
                    optionsLayout.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = 200
        optionsLayout.startAnimation(a)
    }
}