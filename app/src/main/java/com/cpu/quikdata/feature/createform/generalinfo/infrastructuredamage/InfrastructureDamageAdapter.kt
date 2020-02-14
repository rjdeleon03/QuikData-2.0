package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.InfraCategories
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_infrastructure_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class InfrastructureDamageAdapter @AssistedInject constructor (
    context: Context,
    @Assisted rowSaveListener: (InfrastructureDamageRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<InfrastructureDamageRow, InfrastructureDamageAdapter.ViewHolder>
        (context, R.layout.item_infrastructure_damage, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (InfrastructureDamageRow) -> Unit, expandedItem: Int): InfrastructureDamageAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

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