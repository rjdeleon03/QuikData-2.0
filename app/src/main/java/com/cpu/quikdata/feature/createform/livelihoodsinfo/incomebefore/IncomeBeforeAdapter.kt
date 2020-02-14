package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_income.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class IncomeBeforeAdapter @AssistedInject constructor (
    context: Context,
    @Assisted rowSaveListener: (IncomeBeforeRow) -> Unit,
    @Assisted deleteClickListener: (IncomeBeforeRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseAssistanceAdapter<IncomeBeforeRow, IncomeBeforeAdapter.ViewHolder>(context, rowSaveListener, deleteClickListener, R.layout.item_income, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (IncomeBeforeRow) -> Unit,
                   deleteClickListener: (IncomeBeforeRow) -> Unit,
                   expandedItem: Int): IncomeBeforeAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<IncomeBeforeRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: IncomeBeforeRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (IncomeBeforeRow) -> Unit) {

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
                val newRow = IncomeBeforeRow(
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