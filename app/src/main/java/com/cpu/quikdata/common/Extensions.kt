package com.cpu.quikdata.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Outline
import android.net.Uri
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cpu.quikdata.R
import com.cpu.quikdata.dialog.ConfirmationDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.format.DateTimeFormat
import java.io.File
import kotlin.coroutines.CoroutineContext

fun <T> LiveData<T>.observeOnly(lifecycleOwner: LifecycleOwner) {
    observe(lifecycleOwner, Observer {  })
}

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

fun View.addWebLink(url: String, context: Context) {
    this.clickWithGuard {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}

fun EditText.setupNumberInputValidation() {

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
        focusSource.performClick()
    }

    target.setOnClickListener {
        focusSource.performClick()
    }

    focusSource.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            target.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            onFocusAction?.invoke()
        } else {
            target.setTextColor(defaultColor)
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
    val dialog = ConfirmationDialogFragment.start(titleId, layoutId)
    dialog.onPositiveButtonListener = {
        positiveButtonListener.invoke()
        Toast.makeText(this.context!!, toastId, Toast.LENGTH_SHORT).show()
    }
    dialog.show(childFragmentManager, ConfirmationDialogFragment.TAG)
}

fun AppCompatActivity.showConfirmationDialog(positiveButtonListener: () -> Unit,
                                    titleId: Int = R.string.form_item_discard_title,
                                    layoutId: Int = R.layout.dialog_form_item_discard,
                                    toastId: Int = R.string.form_item_discarded) {
    val dialog = ConfirmationDialogFragment.start(titleId, layoutId)
    dialog.onPositiveButtonListener = {
        positiveButtonListener.invoke()
        Toast.makeText(this, toastId, Toast.LENGTH_SHORT).show()
    }
    dialog.show(this.supportFragmentManager, ConfirmationDialogFragment.TAG)
}

fun Fragment.setupClipping(rootLayout: View) {
    rootLayout.outlineProvider = object : ViewOutlineProvider() {
        val curveRadius = resources.getDimension(R.dimen.dimenMid)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, 0, view!!.width, (view.height + curveRadius).toInt(), curveRadius)
        }
    }
    rootLayout.clipToOutline = true
}

fun Activity.setupClipping(rootLayout: ViewGroup) {
    rootLayout.outlineProvider = object : ViewOutlineProvider() {
        val curveRadius = resources.getDimension(R.dimen.dimenMid)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, 0, view!!.width, (view.height + curveRadius).toInt(), curveRadius)
        }
    }
    rootLayout.clipToOutline = true
}

fun Fragment.showToast(textId: Int) {
    Toast.makeText(context!!, textId, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(textId: Int) {
    Toast.makeText(this, textId, Toast.LENGTH_SHORT).show()
}

fun Uri.deleteFile(): Boolean {
    path?.let {
        val file = File(it)
        return try {
            file.delete()
            true
        } catch (ex: Exception) {
            false
        }
    }
    return false
}

fun LiveData<ProgressNotification>.observeProgress(lifecycleOwner: LifecycleOwner,
                                                   onFinishListener: () -> Unit,
                                                   onErrorListener: () -> Unit,
                                                   onCanceledListener: () -> Unit,
                                                   onProgressUpdate: (ProgressNotification) -> Unit) {

    this.observe(lifecycleOwner, Observer {
        when (it) {
            ProgressNotification.FINISHED -> {
                onFinishListener()
            }
            ProgressNotification.ERROR_OCCURRED -> {
                onErrorListener()
            }
            ProgressNotification.CANCELLED -> {
                onCanceledListener()
            }
            else -> {
                onProgressUpdate(it)
            }
        }
    })
}

/**
 * For running coroutines involving IO operations in ViewModel
 */
fun ViewModel.runOnIoThread(task: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) { task() }
}

fun ViewModel.runOnIoThread(contextProvider: CoroutineContextProvider, task: suspend () -> Unit) {
    viewModelScope.launch(contextProvider.IO) { task() }
}

/**
 * For running coroutines involving heavy computations in ViewModel
 */
fun ViewModel.runOnDefaultThread(task: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.Default) { task() }
}

/**
 * For running coroutines involving UI operations in ViewModel
 */
fun ViewModel.runOnUiThread(task: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.Main) { task() }
}