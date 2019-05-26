package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_string.view.*

class MultilineStringQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_multiline_string, this)
        orientation = LinearLayout.VERTICAL

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultilineStringQuestion)
        textLayout.hint = attributes.getString(R.styleable.MultilineStringQuestion_textHint)
        textField.setText(attributes.getString(R.styleable.MultilineStringQuestion_text))
        attributes.recycle()
    }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}