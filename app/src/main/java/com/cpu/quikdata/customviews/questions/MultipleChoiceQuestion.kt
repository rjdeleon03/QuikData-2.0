package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.children
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_multiple_choice.view.*

class MultipleChoiceQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mDefaultColor = 0

    init {
        View.inflate(context, R.layout.question_multiple_choice, this)
        orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MultipleChoiceQuestion)
        questionChoiceText.text = attributes.getString(R.styleable.MultipleChoiceQuestion_questionChoice)
        attributes.recycle()

        val attrs2 = intArrayOf(android.R.attr.textColorHint)
        val defaultAttributes = context.theme.obtainStyledAttributes(attrs2)
        mDefaultColor = defaultAttributes.getColor(0, Color.RED)
        defaultAttributes.recycle()
    }

    fun addItem(stringId: Int, value: Boolean = false) {
        val checkBox = CheckBox(context)
        checkBox.text = resources.getString(stringId)
        checkBox.isChecked = value
        checkBox.setTextColor(mDefaultColor)
        multipleChoiceLayout.addView(checkBox)
    }

    fun getItems(): List<Boolean> {
        val booleanList = ArrayList<Boolean>()
        multipleChoiceLayout.children.forEach {
            if (it is CheckBox) {
                booleanList.add(it.isChecked)
            }
        }
        return booleanList
    }
}