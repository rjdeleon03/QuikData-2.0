package com.cpu.quikdata.customviews.questions

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.question_date.view.*
import org.joda.time.LocalDate

@SuppressLint("SetTextI18n")
class DateQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mOnDateSetListener: ((Int, Int, Int) -> Unit)? = null
    private var mDate: LocalDate = LocalDate.now()

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
                    mDate = LocalDate(year, month, dayOfMonth)
                    setDateOnText()
                },
                mDate.year, mDate.monthOfYear, mDate.dayOfMonth)
            dialog.show()
        }
    }

    var date: Long
        get() = mDate.toDateTimeAtStartOfDay().millis
        set(value) {
            mDate = LocalDate(value)
            setDateOnText()
        }

    fun setOnDateSetListener(listener: (Int, Int, Int) -> Unit) {
        mOnDateSetListener = listener
    }

    private fun setDateOnText() {
        textField.setText("${mDate.year}/${mDate.monthOfYear}/${mDate.dayOfMonth}")
    }
}