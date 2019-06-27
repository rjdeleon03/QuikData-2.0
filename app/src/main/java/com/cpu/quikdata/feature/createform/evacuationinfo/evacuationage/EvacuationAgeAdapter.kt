package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import kotlinx.android.synthetic.main.item_evacuation_age.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class EvacuationAgeAdapter(context: Context, rowSaveListener: (EvacuationAgeRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<EvacuationAgeRow, EvacuationAgeAdapter.ViewHolder>(context, R.layout.item_evacuation_age, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<EvacuationAgeRow>(itemView) {

        override fun populateWithDataInternal(row: EvacuationAgeRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (EvacuationAgeRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.evacuationAgeMaleText.number = row.male }
            UIJobScheduler.submitJob { view.evacuationAgeFemaleText.number = row.female }
            UIJobScheduler.submitJob { view.evacuationAgeLgbtText.number = row.lgbt }
            UIJobScheduler.submitJob { view.evacuationAgePregnantText.number = row.pregnant }
            UIJobScheduler.submitJob { view.evacuationAgeLactatingText.number = row.lactating }
            UIJobScheduler.submitJob { view.evacuationAgeDisabledText.number = row.disabled }
            UIJobScheduler.submitJob { view.evacuationAgeSickText.number = row.sick }

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = EvacuationAgeRow(
                    row.id,
                    row.type,
                    view.evacuationAgeMaleText.number,
                    view.evacuationAgeFemaleText.number,
                    view.evacuationAgeLgbtText.number,
                    view.evacuationAgePregnantText.number,
                    view.evacuationAgeLactatingText.number,
                    view.evacuationAgeDisabledText.number,
                    view.evacuationAgeSickText.number,
                    row.evacuationId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}