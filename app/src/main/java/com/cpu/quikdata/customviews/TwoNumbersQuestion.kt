package com.cpu.quikdata.customviews

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupNumberInputValidation
import kotlinx.android.synthetic.main.question_two_numbers.view.*

class TwoNumbersQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_two_numbers, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TwoNumbersQuestion)
        questionText.text = attributes.getString(R.styleable.TwoNumbersQuestion_question)
        textLayout1.hint = attributes.getString(R.styleable.TwoNumbersQuestion_textHint1)
        textField1.setText(attributes.getString(R.styleable.TwoNumbersQuestion_text1))
        textLayout2.hint = attributes.getString(R.styleable.TwoNumbersQuestion_textHint2)
        textField2.setText(attributes.getString(R.styleable.TwoNumbersQuestion_text2))

        attributes.recycle()

        textField1.setupNumberInputValidation()
        textField2.setupNumberInputValidation()
    }
}