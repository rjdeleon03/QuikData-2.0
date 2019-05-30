package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_string.view.*

class StringQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_string, this)
        orientation = LinearLayout.VERTICAL

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StringQuestion)
        textLayout.hint = attributes.getString(R.styleable.StringQuestion_textHint)
        textField.setText(attributes.getString(R.styleable.StringQuestion_text))
        attributes.recycle()
    }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}