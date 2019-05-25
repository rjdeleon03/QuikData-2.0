package com.cpu.quikdata.customviews.questions

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_date.view.*

@SuppressLint("SetTextI18n")
class DateQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mOnDateSetListener: ((Int, Int, Int) -> Unit)? = null

    init {
        View.inflate(context, R.layout.question_date, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DateQuestion)
        textLayout.hint = attributes.getString(R.styleable.DateQuestion_question)
        textField.setText(attributes.getString(R.styleable.DateQuestion_text))
        attributes.recycle()

        textField.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) return@setOnFocusChangeListener

            val dialog = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    mOnDateSetListener?.invoke(year, month, dayOfMonth)
                    textField.setText("$year/$month/$dayOfMonth")
                },
                1970, 1, 1)
            dialog.show()
        }
    }

    fun setOnDateSetListener(listener: (Int, Int, Int) -> Unit) {
        mOnDateSetListener = listener
    }
}