package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_three_choice_multiline_string_long.view.*

class ThreeChoiceMultilineStringLongQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mOnInfoClickListener: (() -> Unit)? = null

    init {
        View.inflate(context, R.layout.question_three_choice_multiline_string_long, this)
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, android.R.color.white)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ThreeChoiceMultilineStringLongQuestion)
        val questionChoice = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_questionChoice)
        val questionString = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_questionString)
        val option1Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option1Text)
        val option2Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option2Text)
        val option3Text = attributes.getString(R.styleable.ThreeChoiceMultilineStringLongQuestion_option3Text)
        attributes.recycle()
        questionChoiceText.text = questionChoice
        if (option1Text != null) radioOption1.text = option1Text
        if (option2Text != null) radioOption2.text = option2Text
        if (option3Text != null) radioOption3.text = option3Text
        questionStringText.hint = questionString

        radioInfoButton.clickWithGuard {
            mOnInfoClickListener?.invoke()
        }

        setupOnFocusBehavior(questionStringText, textField)
    }

    var value: Int
        get() = when {
            radioOption1.isChecked -> 0
            radioOption2.isChecked -> 1
            else -> 2
        }
        set(value) {
            when (value) {
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

    var onInfoClickListener: (() -> Unit)? = null
        set (value) {
            field = value
            mOnInfoClickListener = field
        }
}