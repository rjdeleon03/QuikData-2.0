package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import kotlinx.android.synthetic.main.item_psychosocial.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PsychosocialAdapter(context: Context, rowSaveListener: (PsychosocialRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<PsychosocialRow, PsychosocialAdapter.ViewHolder>(context, R.layout.item_psychosocial, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<PsychosocialRow>(itemView) {

        override fun populateWithDataInternal(row: PsychosocialRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (PsychosocialRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.psychosocialCasesText.number1 = row.casesMale }
            UIJobScheduler.submitJob { view.psychosocialCasesText.number2 = row.casesFemale }
            UIJobScheduler.submitJob { view.psychosocialManifestationsText.text = row.manifestations }
            UIJobScheduler.submitJob { view.psychosocialNeedsText.text = row.needs }

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