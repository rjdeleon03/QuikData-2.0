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
import kotlinx.android.synthetic.main.question_number.view.*

class NumberQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_number, this)
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, android.R.color.white)

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.NumberQuestion)
        val question = attributes.getString(R.styleable.NumberQuestion_question)
        val text = attributes.getString(R.styleable.NumberQuestion_text)
        questionText.text = question
        textField.setText(text)
        attributes.recycle()

        textField.setupNumberInputValidation()
        setupOnFocusBehavior(questionText, textField)
    }

    var number: Int
        get() {
            return textField.text.toString().toIntOrNull() ?: 0
        }
        set(value) {
            textField.setText(value.toString())
        }
}