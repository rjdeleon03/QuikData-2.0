package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.common.setupNumberInputValidation
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_two_numbers.view.*

class TwoNumbersQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_two_numbers, this)
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, android.R.color.white)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TwoNumbersQuestion)
        val question = attributes.getString(R.styleable.TwoNumbersQuestion_question)
        val textHint1 = attributes.getString(R.styleable.TwoNumbersQuestion_textHint1)
        val textHint2 = attributes.getString(R.styleable.TwoNumbersQuestion_textHint2)
        val text1 = attributes.getString(R.styleable.TwoNumbersQuestion_text1)
        val text2 = attributes.getString(R.styleable.TwoNumbersQuestion_text2)
        questionText.text = question
        textLabel1.text = textHint1
        textField1.setText(text1)
        textLabel2.text = textHint2
        textField2.setText(text2)

        attributes.recycle()
        textField1.setupNumberInputValidation()
        textField2.setupNumberInputValidation()
        layout1.setupOnFocusBehavior(textLabel1, textField1)
        layout1.setupOnFocusBehavior(textLabel2, textField2)
    }

    var number1: Int
        get() = textField1.text.toString().toIntOrNull() ?: 0
        set(value) {
            textField1.setText(value.toString())
        }

    var number2: Int
        get() = textField2.text.toString().toIntOrNull() ?: 0
        set(value) {
            textField2.setText(value.toString())
        }
}