package com.cpu.quikdata.customviews.questions

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupOnFocusBehavior
import kotlinx.android.synthetic.main.question_date.view.*
import org.joda.time.LocalDate

@Suppress("unused")
@SuppressLint("SetTextI18n")
class DateQuestion(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mOnDateSetListener: ((Int, Int, Int) -> Unit)? = null
    private var mDate: LocalDate = LocalDate.now()

    init {
        View.inflate(context, R.layout.question_date, this)
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, android.R.color.white)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DateQuestion)
        questionText.hint = attributes.getString(R.styleable.DateQuestion_question)
        textField.setText(attributes.getString(R.styleable.DateQuestion_text))
        attributes.recycle()

        // Ensure that all clicks in children trigger the parent's onClick listener
        setupOnFocusBehavior(questionText, textField) {openDatePicker()}
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

    private fun openDatePicker() {

        val dialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mOnDateSetListener?.invoke(year, month + 1, dayOfMonth)
                mDate = LocalDate(year, month + 1, dayOfMonth)
                setDateOnText()
            },
            mDate.year, mDate.monthOfYear - 1, mDate.dayOfMonth)
        dialog.show()
    }
}