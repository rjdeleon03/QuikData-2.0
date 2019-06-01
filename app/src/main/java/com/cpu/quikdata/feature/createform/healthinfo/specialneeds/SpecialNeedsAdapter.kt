package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.SpecialNeedsCategories
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import kotlinx.android.synthetic.main.item_special_needs.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class SpecialNeedsAdapter(context: Context, rowSaveListener: (SpecialNeedsRow) -> Unit) :
    BaseAdapter<SpecialNeedsRow, SpecialNeedsAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_special_needs, parent, false)
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

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<SpecialNeedsRow>(itemView) {

        override fun populateWithData(row: SpecialNeedsRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (SpecialNeedsRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(SpecialNeedsCategories.getStringId(row.type))
            view.specialNeedsNumberText.number = row.number
            view.specialNeedsHealthMedicalText.text = row.healthMedical

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = SpecialNeedsRow(
                    row.id,
                    row.type,
                    view.specialNeedsNumberText.number,
                    view.specialNeedsHealthMedicalText.text,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}