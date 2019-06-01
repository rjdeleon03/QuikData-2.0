package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_three_choice_multiline_string_long.view.*

class ThreeChoiceMultilineStringLongQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_three_choice_multiline_string_long, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ThreeChoiceMultilineStringLongQuestion)
        questionChoiceText.text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_questionChoice)
        val option1Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option1Text)
        val option2Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option2Text)
        val option3Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option3Text)
        if (option1Text != null) radioOption1.text = option1Text
        if (option2Text != null) radioOption2.text = option2Text
        if (option3Text != null) radioOption3.text = option3Text
        questionStringText.hint = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_questionString)
        attributes.recycle()

        setupOnFocusBehavior(questionStringText, textField)
    }

    var value: Int
        get() = when {
            radioOption1.isChecked -> 0
            radioOption2.isChecked -> 1
            else -> 2
        }
        set(value) {
            when(value) {
                0 -> radioOption1.isChecked = true
                1 -> radioOption2.isChecked = true
                else -> radioOption3.isChecked = true
            }
        }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}