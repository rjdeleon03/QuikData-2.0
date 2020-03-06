package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_income.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class IncomeAfterAdapter @AssistedInject constructor(
    context: Context,
    @Assisted rowSaveListener: (IncomeAfterRow) -> Unit,
    @Assisted deleteClickListener: (IncomeAfterRow) -> Unit,
    @Assisted expandedItemIndex: Int = 0) :
    BaseAssistanceAdapter<IncomeAfterRow, IncomeAfterAdapter.ViewHolder>(context, rowSaveListener, deleteClickListener, R.layout.item_income, expandedItemIndex) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (IncomeAfterRow) -> Unit,
                   deleteClickListener: (IncomeAfterRow) -> Unit,
                   expandedItemIndex: Int): IncomeAfterAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<IncomeAfterRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: IncomeAfterRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (IncomeAfterRow) -> Unit) {

            view.tag = idx
            view.headerTextField.text = view.resources.getString(R.string.income_source_item)
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