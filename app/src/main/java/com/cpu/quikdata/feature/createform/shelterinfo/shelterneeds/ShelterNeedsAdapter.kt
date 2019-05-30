package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.MaterialCategories
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import kotlinx.android.synthetic.main.item_shelter_needs.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class ShelterNeedsAdapter(context: Context, rowSaveListener: (ShelterNeedsRow) -> Unit) :
    BaseAdapter<ShelterNeedsRow, ShelterNeedsAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_shelter_needs, parent, false)
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

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<ShelterNeedsRow>(itemView) {

        override fun populateWithData(row: ShelterNeedsRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (ShelterNeedsRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(MaterialCategories.getStringId(row.type))
            view.shelterNeedsSpecificItemsText.text = row.specificItems
            view.shelterNeedsFamiliesInNeedText.number = row.familiesInNeed

            // Setup listener for saving each population row
            collapsibleView?.onDetachedListener = {
                val newRow = ShelterNeedsRow(
                    row.id,
                    row.type,
                    view.shelterNeedsSpecificItemsText.text,
                    view.shelterNeedsFamiliesInNeedText.number,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}