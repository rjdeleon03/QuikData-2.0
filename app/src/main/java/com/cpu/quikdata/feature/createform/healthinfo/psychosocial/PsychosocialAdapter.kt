package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import kotlinx.android.synthetic.main.item_psychosocial.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PsychosocialAdapter(context: Context, rowSaveListener: (PsychosocialRow) -> Unit) :
    BaseAdapter<PsychosocialRow, PsychosocialAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_psychosocial, parent, false)
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

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<PsychosocialRow>(itemView) {

        override fun populateWithData(row: PsychosocialRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (PsychosocialRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
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