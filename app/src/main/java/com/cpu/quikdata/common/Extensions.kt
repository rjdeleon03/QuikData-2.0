package com.cpu.quikdata.common

import android.graphics.Color
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.ConfirmationDialogFragment
import com.google.android.material.textfield.TextInputEditText
import org.joda.time.format.DateTimeFormat


fun <T> LiveData<T>.observeExceptFirst(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    var isFirstEmit = true
    observe(lifecycleOwner, Observer<T> {
        if (isFirstEmit) {
            isFirstEmit = false
        } else {
            observer.onChanged(it)
        }
    })
}

fun Long.toDateString(): String {
    val formatter = DateTimeFormat.forPattern("yyyy/MM/dd")
    return formatter.print(this)
}

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

fun ViewGroup.setupOnFocusBehavior(target: TextView, focusSource: View, onFocusAction: (() -> Unit)? = null) {

    /* Retrieve default text view color */
    val attrs2 = intArrayOf(android.R.attr.textColorHint)
    val defaultAttributes = context.theme.obtainStyledAttributes(attrs2)
    val defaultColor = defaultAttributes.getColor(0, Color.RED)
    defaultAttributes.recycle()

    setOnClickListener {
        focusSource.requestFocus()
    }

    focusSource.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            target.setHintTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            onFocusAction?.invoke()
        } else {
            target.setHintTextColor(defaultColor)
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

fun Fragment.showConfirmationDialog(positiveButtonListener: () -> Unit,
                                    titleId: Int = R.string.assistance_delete_confirmation,
                                    layoutId: Int = R.layout.dialog_assistance_delete,
                                    toastId: Int = R.string.assistance_delete_finished) {
    val dialog = ConfirmationDialogFragment.newInstance(titleId, layoutId)
    dialog.onPositiveButtonListener = {
        positiveButtonListener.invoke()
        Toast.makeText(this.context!!, toastId, Toast.LENGTH_SHORT).show()
    }
    dialog.show(childFragmentManager, ConfirmationDialogFragment.TAG)
}

/*
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
*/