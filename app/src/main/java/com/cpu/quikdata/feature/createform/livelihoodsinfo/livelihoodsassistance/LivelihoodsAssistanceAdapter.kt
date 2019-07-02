package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import kotlinx.android.synthetic.main.item_assistance.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class LivelihoodsAssistanceAdapter(context: Context,
                                   rowSaveListener: (LivelihoodsAssistanceRow) -> Unit,
                                   deleteClickListener: (LivelihoodsAssistanceRow) -> Unit,
                                   expandedItem: Int = 0) :
    BaseAssistanceAdapter<LivelihoodsAssistanceRow, LivelihoodsAssistanceAdapter.ViewHolder>
        (context, rowSaveListener, deleteClickListener, expandedItem = expandedItem) {

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<LivelihoodsAssistanceRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: LivelihoodsAssistanceRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (LivelihoodsAssistanceRow) -> Unit) {

            view.tag = idx
            view.headerTextField.text = "${view.resources.getString(R.string.assistance_item)} ${idx + 1}"
            UIJobScheduler.submitJob {
                view.assistanceOrganizationText.text = row.organizationAgency
                 view.assistanceTypeText.text = row.assistanceType
                 view.assistanceDateReceivedText.date = row.dateReceived
                 view.assistanceQuantityText.text = row.quantity
                 view.assistanceMenText.number = row.beneficiariesMen
                 view.assistanceWomenText.number = row.beneficiariesWomen
                 view.assistanceBoysText.number = row.beneficiariesBoys
                 view.assistanceGirlsText.number = row.beneficiariesGirls
            }

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = LivelihoodsAssistanceRow(
                    row.id,
                    view.assistanceOrganizationText.text,
                    view.assistanceTypeText.text,
                    view.assistanceDateReceivedText.date,
                    view.assistanceQuantityText.text,
                    view.assistanceMenText.number,
                    view.assistanceWomenText.number,
                    view.assistanceBoysText.number,
                    view.assistanceGirlsText.number,
                    row.dateCreated,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}