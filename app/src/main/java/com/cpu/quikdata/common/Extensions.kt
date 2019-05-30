package com.cpu.quikdata.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_population.*
import kotlinx.android.synthetic.main.question_two_numbers.view.*
import kotlin.math.roundToInt

fun View.clickWithGuard(guardTime: Long = 500L, action: () -> Unit) {

    this.setOnClickListener(object: View.OnClickListener {
        private var lastClickTime = 0L

        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < guardTime) return
            else action.invoke()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun TextInputEditText.setupNumberInputValidation() {

    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            val intValue = this.text.toString().toIntOrNull()
            if (intValue == null) {
                this.setText("0")
            }
        }
    }
}

fun ViewPager.setupViewPager(pagerAdapter: PagerAdapter,
                             titleChangedListener: (String) -> Unit) {

    this.adapter = pagerAdapter
    this.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            titleChangedListener.invoke(pagerAdapter.getPageTitle(position).toString())
        }

        override fun onPageSelected(position: Int) {}
    })
}

fun RecyclerView.setupTapToExpand(context: Context) {
    var startX = 0F
    var startY = 0F
    this.setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_UP -> {
                if (isClickAction(startX, startY, event.x, event.y, context)) {
                    this.children.forEach {
                        if (it is CollapsibleContainer) {
                            val viewRect = Rect()
                            it.getHitRect(viewRect)
                            if (viewRect.contains(event.x.roundToInt(), event.y.roundToInt())) {
                                it.expand()
                            } else {
                                it.collapse()
                            }
                        }
                    }
                }
            }
        }
        false
    }
}

private fun isClickAction(beforeX: Float, beforeY: Float, afterX: Float, afterY: Float, context: Context): Boolean{
    val tolerance = ViewConfiguration.get(context).scaledTouchSlop
    return Math.abs(afterX - beforeX) <= tolerance && Math.abs(afterY - beforeY) <= tolerance
}