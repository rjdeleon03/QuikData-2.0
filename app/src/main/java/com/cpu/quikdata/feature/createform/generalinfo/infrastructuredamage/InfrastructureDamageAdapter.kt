package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.InfraCategories
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import kotlinx.android.synthetic.main.item_infrastructure_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class InfrastructureDamageAdapter(context: Context, rowSaveListener: (InfrastructureDamageRow) -> Unit) :
    BaseCollapsibleAdapter<InfrastructureDamageRow, InfrastructureDamageAdapter.ViewHolder>(context, R.layout.item_infrastructure_damage, rowSaveListener) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<InfrastructureDamageRow>(itemView) {


        override fun populateWithDataInternal(row: InfrastructureDamageRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (InfrastructureDamageRow) -> Unit) {

            view.tag = idx
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