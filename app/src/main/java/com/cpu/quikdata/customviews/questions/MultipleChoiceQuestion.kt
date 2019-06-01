package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_three_choice_multiline_string_long.view.*

class MultipleChoiceQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.question_three_choice_multiline_string_long, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultipleChoiceQuestion)
        questionChoiceText.text = attributes.getString(R.styleable.MultipleChoiceQuestion_questionChoice)
        attributes.recycle()

        setupOnFocusBehavior(questionStringText, textField)
    }
}