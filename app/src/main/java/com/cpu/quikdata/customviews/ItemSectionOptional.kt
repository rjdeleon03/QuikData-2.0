package com.cpu.quikdata.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import kotlinx.android.synthetic.main.view_item_section.view.mainButton
import kotlinx.android.synthetic.main.view_item_section_optional.view.*

class ItemSectionOptional(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mMainButtonListener: (() -> Unit)? = null
    private var mMainSwitchListener: ((Boolean) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_item_section_optional, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSectionOptional)
        mainButton.text = attributes.getString(R.styleable.ItemSectionOptional_text)
        mainSwitch.isChecked = attributes.getBoolean(R.styleable.ItemSectionOptional_include, false)
        attributes.recycle()

        mainButton.clickWithGuard {
            mMainButtonListener?.invoke()
        }
        mainSwitch.setOnCheckedChangeListener { _, isChecked ->
            mMainSwitchListener?.invoke(isChecked)
        }
    }

    var isIncluded: Boolean = false
        get() = mainSwitch.isChecked
        set(value) {
            field = value
            mainSwitch.isChecked = field
        }

    fun setButtonListeners(main: () -> Unit, switch: ((Boolean) -> Unit)? = null) {
        mMainButtonListener = main
        mMainSwitchListener = switch
    }
}