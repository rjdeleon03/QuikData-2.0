package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import kotlinx.android.synthetic.main.item_assistance.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class WashAssistanceAdapter(context: Context, rowSaveListener: (WashAssistanceRow) -> Unit) :
    BaseAssistanceAdapter<WashAssistanceRow, WashAssistanceAdapter.ViewHolder>(context, rowSaveListener) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<WashAssistanceRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: WashAssistanceRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (WashAssistanceRow) -> Unit) {

            view.tag = idx
            view.headerTextField.text = "${view.resources.getString(R.string.assistance_item)} ${idx + 1}"
            view.assistanceOrganizationText.text = row.organizationAgency
            view.assistanceTypeText.text = row.assistanceType
            view.assistanceDateReceivedText.date = row.dateReceived
            view.assistanceQuantityText.text = row.quantity
            view.assistanceMenText.number = row.beneficiariesMen
            view.assistanceWomenText.number = row.beneficiariesWomen
            view.assistanceBoysText.number = row.beneficiariesBoys
            view.assistanceGirlsText.number = row.beneficiariesGirls

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = WashAssistanceRow(
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