package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.CustomArrayAdapter
import com.cpu.quikdata.common.EvacuationCategories
import kotlinx.android.synthetic.main.question_multiple_choice.view.questionChoiceText
import kotlinx.android.synthetic.main.question_multiple_choice_dropdown.view.*

class MultipleChoiceDropdownQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mDefaultColor: Int
    private var mColorStateList: ColorStateList

    init {
        View.inflate(context, R.layout.question_multiple_choice_dropdown, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultipleChoiceDropdownQuestion)
        questionChoiceText.text = attributes.getString(R.styleable.MultipleChoiceDropdownQuestion_questionChoice)
        attributes.recycle()

        val attrs2 = intArrayOf(android.R.attr.textColorHint)
        val defaultAttributes = context.theme.obtainStyledAttributes(attrs2)
        mDefaultColor = defaultAttributes.getColor(0, Color.RED)
        defaultAttributes.recycle()

        mColorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(
                mDefaultColor,
                ContextCompat.getColor(context, R.color.colorAccent)
            )
        )
    }

    var items: List<Int>? = null
        set(value) {
            field = value
            if (field != null) {
                val adapter = CustomArrayAdapter(context, field!!)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                questionChoiceSpinner.adapter = adapter
            }
        }

    var selectedIndex: Int = 0
        get() = questionChoiceSpinner.selectedItemPosition
        set(value) {
            field = value
            questionChoiceSpinner.setSelection(field)
        }
}