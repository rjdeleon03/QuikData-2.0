package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_boolean.view.*

class BooleanQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_boolean, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BooleanQuestion)
        questionText.text = attributes.getString(R.styleable.BooleanQuestion_question)
        val yesText = attributes.getString(R.styleable.BooleanQuestion_yesText)
        val noText = attributes.getString(R.styleable.BooleanQuestion_noText)
        if (yesText != null) radioYes.text = yesText
        if (noText != null) radioNo.text = noText

        attributes.recycle()
    }

    var value: Boolean
        get() = radioYes.isChecked
        set(value) {
            radioYes.isChecked = value
            radioNo.isChecked = !value
        }
}