package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_multiline_string_long.view.*

class MultilineStringLongQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_multiline_string_long, this)
        orientation = LinearLayout.VERTICAL

        /* Retrieve view attributes then apply */
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultilineStringLongQuestion)
        questionText.hint = attributes.getString(R.styleable.MultilineStringLongQuestion_textHint)
        textField.setText(attributes.getString(R.styleable.MultilineStringLongQuestion_text))
        attributes.recycle()

        /* Retrieve default text view color */
        val attrs2 = intArrayOf(android.R.attr.textColorHint)
        val defaultAttributes = context.theme.obtainStyledAttributes(attrs2)
        val defaultColor = defaultAttributes.getColor(0, Color.RED)
        defaultAttributes.recycle()

        setOnClickListener {
            textField.requestFocus()
        }

        textField.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                questionText.setHintTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            } else {
                questionText.setHintTextColor(defaultColor)
            }
        }
    }

    var text: String
        get() = textField.text.toString()
        set(value) {
            textField.setText(value)
        }
}