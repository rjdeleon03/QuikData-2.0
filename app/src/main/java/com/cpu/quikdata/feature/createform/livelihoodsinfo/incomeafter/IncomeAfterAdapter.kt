package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import kotlinx.android.synthetic.main.item_income.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class IncomeAfterAdapter(context: Context, rowSaveListener: (IncomeAfterRow) -> Unit) :
    BaseAdapter<IncomeAfterRow, IncomeAfterAdapter.ViewHolder>(context, R.layout.item_income, rowSaveListener) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<IncomeAfterRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: IncomeAfterRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (IncomeAfterRow) -> Unit) {

            view.tag = idx
            view.headerTextField.text = "${view.resources.getString(R.string.income_source_item)} ${idx + 1}"
            view.incomeSourceText.text = row.source
            view.incomeSourceTypeText.value = row.isPrimary
            view.incomeAverageIncomeText.number = row.income
            view.incomeHouseholdsText.number = row.households
            view.incomeMaleText.number = row.male
            view.incomeFemaleText.number = row.female
            view.incomeBoysText.number = row.boys
            view.incomeGirlsText.number = row.girls

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = IncomeAfterRow(
                    row.id,
                    view.incomeSourceText.text,
                    view.incomeSourceTypeText.value,
                    view.incomeAverageIncomeText.number,
                    view.incomeHouseholdsText.number,
                    view.incomeMaleText.number,
                    view.incomeFemaleText.number,
                    view.incomeBoysText.number,
                    view.incomeGirlsText.number,
                    row.dateCreated,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}