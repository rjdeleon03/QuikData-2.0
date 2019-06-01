package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.InfraCategories
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import kotlinx.android.synthetic.main.item_infrastructure_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class InfrastructureDamageAdapter(context: Context, rowSaveListener: (InfrastructureDamageRow) -> Unit) :
    BaseAdapter<InfrastructureDamageRow, InfrastructureDamageAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_infrastructure_damage, parent, false)
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

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<InfrastructureDamageRow>(itemView) {

        override fun populateWithData(row: InfrastructureDamageRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (InfrastructureDamageRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(InfraCategories.getStringId(row.type))
            view.infrastructureDamageNumberText.number = row.numberOfInfrastructure
            view.infrastructureDamageFunctionalBool.value = row.isFunctional
            view.infrastructureDamageRemarksText.text = row.remarks

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = InfrastructureDamageRow(
                    row.id,
                    row.type,
                    view.infrastructureDamageNumberText.number,
                    view.infrastructureDamageFunctionalBool.value,
                    view.infrastructureDamageRemarksText.text,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}