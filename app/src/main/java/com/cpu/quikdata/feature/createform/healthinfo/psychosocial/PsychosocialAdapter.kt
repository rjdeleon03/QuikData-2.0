package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_psychosocial.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PsychosocialAdapter @AssistedInject constructor(
    context: Context,
    @Assisted rowSaveListener: (PsychosocialRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<PsychosocialRow, PsychosocialAdapter.ViewHolder>(context, R.layout.item_psychosocial, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (PsychosocialRow) -> Unit, expandedItem: Int): PsychosocialAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<PsychosocialRow>(itemView) {

        override fun populateWithDataInternal(row: PsychosocialRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (PsychosocialRow) -> Unit) {

            view.tag = idx
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.psychosocialCasesText.number1 = row.casesMale
            view.psychosocialCasesText.number2 = row.casesFemale
            view.psychosocialManifestationsText.text = row.manifestations
            view.psychosocialNeedsText.text = row.needs

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = PsychosocialRow(
                    row.id,
                    row.type,
                    view.psychosocialCasesText.number1,
                    view.psychosocialCasesText.number2,
                    view.psychosocialManifestationsText.text,
                    view.psychosocialNeedsText.text,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}