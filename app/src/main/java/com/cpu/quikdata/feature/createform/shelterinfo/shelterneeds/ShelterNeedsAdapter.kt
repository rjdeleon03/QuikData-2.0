package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.MaterialCategories
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import kotlinx.android.synthetic.main.item_shelter_needs.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class ShelterNeedsAdapter(context: Context, rowSaveListener: (ShelterNeedsRow) -> Unit) :
    BaseCollapsibleAdapter<ShelterNeedsRow, ShelterNeedsAdapter.ViewHolder>(context, R.layout.item_shelter_needs, rowSaveListener) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<ShelterNeedsRow>(itemView) {

        override fun populateWithDataInternal(row: ShelterNeedsRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (ShelterNeedsRow) -> Unit) {

            view.tag = idx
            view.headerTextField.setText(MaterialCategories.getStringId(row.type))
            view.shelterNeedsSpecificItemsText.text = row.specificItems
            view.shelterNeedsFamiliesInNeedText.number = row.familiesInNeed

            // Setup listener for saving each row
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