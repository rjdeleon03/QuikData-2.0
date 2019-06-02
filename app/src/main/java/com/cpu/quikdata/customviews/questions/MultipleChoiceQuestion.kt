package com.cpu.quikdata.customviews.questions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_multiple_choice.view.*



class MultipleChoiceQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mDefaultColor: Int
    private var mColorStateList: ColorStateList

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

        mColorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(
                mDefaultColor,
                ContextCompat.getColor(context, R.color.colorAccent)
            )
        )
    }

    fun addItem(stringId: Int, value: Boolean = false) {
        val checkBox = AppCompatCheckBox(context)
        checkBox.text = resources.getString(stringId)
        checkBox.isChecked = value
        checkBox.buttonTintList = mColorStateList
        checkBox.setTextColor(mDefaultColor)
        multipleChoiceLayout.addView(checkBox)

        val lp = checkBox.layoutParams as GridLayout.LayoutParams
        lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        checkBox.layoutParams = lp
    }

    fun getItems(): List<Boolean> {
        val booleanList = ArrayList<Boolean>()
        multipleChoiceLayout.children.forEach {
            if (it is AppCompatCheckBox) {
                booleanList.add(it.isChecked)
            }
        }
        return booleanList
    }
}