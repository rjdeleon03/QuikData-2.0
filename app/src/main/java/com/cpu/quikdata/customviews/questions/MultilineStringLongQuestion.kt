package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_multiline_string_long.view.*

class MultilineStringLongQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_multiline_string_long, this)
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, android.R.color.white)

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultilineStringLongQuestion)
        questionText.hint = attributes.getString(R.styleable.MultilineStringLongQuestion_textHint)
        textField.setText(attributes.getString(R.styleable.MultilineStringLongQuestion_text))
        attributes.recycle()
        setupOnFocusBehavior(questionText, textField)
    }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}