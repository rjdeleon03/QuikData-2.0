package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.SpecialNeedsCategories
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_special_needs.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class SpecialNeedsAdapter @AssistedInject constructor (
    context: Context,
    @Assisted rowSaveListener: (SpecialNeedsRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<SpecialNeedsRow, SpecialNeedsAdapter.ViewHolder>(context, R.layout.item_special_needs, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (SpecialNeedsRow) -> Unit, expandedItem: Int): SpecialNeedsAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<SpecialNeedsRow>(itemView) {

        override fun populateWithDataInternal(row: SpecialNeedsRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (SpecialNeedsRow) -> Unit) {

            view.tag = idx
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