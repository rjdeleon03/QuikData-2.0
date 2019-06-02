package com.cpu.quikdata.feature.createform.generalinfo.casualties

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import kotlinx.android.synthetic.main.item_casualties.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class CasualtiesAdapter(context: Context, rowSaveListener: (CasualtiesRow) -> Unit) :
    BaseAdapter<CasualtiesRow, CasualtiesAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_casualties, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, row.type,
            mExpandedItem != row.type, mRowSaveListener,
            { idx, isCollapsed ->
                if (!isCollapsed) mExpandedItem = idx
            })
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<CasualtiesRow>(itemView) {

        override fun populateWithData(row: CasualtiesRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (CasualtiesRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.casualtiesDeadText.number1 = row.deadMale
            view.casualtiesDeadText.number2 = row.deadFemale
            view.casualtiesMissingText.number1 = row.missingMale
            view.casualtiesMissingText.number2 = row.missingFemale
            view.casualtiesInjuredText.number1 = row.injuredMale
            view.casualtiesInjuredText.number2 = row.injuredFemale

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = CasualtiesRow(
                    row.id,
                    row.type,
                    view.casualtiesDeadText.number1,
                    view.casualtiesDeadText.number2,
                    view.casualtiesMissingText.number1,
                    view.casualtiesMissingText.number2,
                    view.casualtiesInjuredText.number1,
                    view.casualtiesInjuredText.number2,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}