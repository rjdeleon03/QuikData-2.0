package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_boolean_multiline_string_long.view.*

class BooleanMultilineStringLongQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_boolean_multiline_string_long, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BooleanMultilineStringLongQuestion)
        questionBooleanText.text = attributes.getString(R.styleable.BooleanMultilineStringLongQuestion_questionBoolean)
        val yesText = attributes.getString(R.styleable.BooleanMultilineStringLongQuestion_yesText)
        val noText = attributes.getString(R.styleable.BooleanMultilineStringLongQuestion_noText)
        if (yesText != null) radioYes.text = yesText
        if (noText != null) radioNo.text = noText
        questionStringText.hint = attributes.getString(R.styleable.BooleanMultilineStringLongQuestion_questionString)
        attributes.recycle()

        setupOnFocusBehavior(questionStringText, textField)
    }

    var value: Boolean
        get() = radioYes.isChecked
        set(value) {
            radioYes.isChecked = value
            radioNo.isChecked = !value
        }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}