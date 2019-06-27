package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow
import kotlinx.android.synthetic.main.item_income.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class IncomeBeforeAdapter(context: Context,
                          rowSaveListener: (IncomeBeforeRow) -> Unit,
                          deleteClickListener: (IncomeBeforeRow) -> Unit,
                          expandedItem: Int = 0) :
    BaseAssistanceAdapter<IncomeBeforeRow, IncomeBeforeAdapter.ViewHolder>(context, rowSaveListener, deleteClickListener, R.layout.item_income, expandedItem) {

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<IncomeBeforeRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: IncomeBeforeRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (IncomeBeforeRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.text = "${view.resources.getString(R.string.income_source_item)} ${idx + 1}" }
            UIJobScheduler.submitJob { view.incomeSourceText.text = row.source }
            UIJobScheduler.submitJob { view.incomeSourceTypeText.value = row.isPrimary }
            UIJobScheduler.submitJob { view.incomeAverageIncomeText.number = row.income }
            UIJobScheduler.submitJob { view.incomeHouseholdsText.number = row.households }
            UIJobScheduler.submitJob { view.incomeMaleText.number = row.male }
            UIJobScheduler.submitJob { view.incomeFemaleText.number = row.female }
            UIJobScheduler.submitJob { view.incomeBoysText.number = row.boys }
            UIJobScheduler.submitJob { view.incomeGirlsText.number = row.girls }

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