package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupNumberInputValidation
import kotlinx.android.synthetic.main.question_string.view.*

class NumberQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_number, this)

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StringQuestion)
        textLayout.hint = attributes.getString(R.styleable.StringQuestion_textHint)
        textField.setText(attributes.getString(R.styleable.StringQuestion_text))
        attributes.recycle()

        textField.setupNumberInputValidation()
    }

    var number: Int
        get() = textField.text.toString().toInt()
        set(value) {
            textField.setText(value.toString())
        }
}