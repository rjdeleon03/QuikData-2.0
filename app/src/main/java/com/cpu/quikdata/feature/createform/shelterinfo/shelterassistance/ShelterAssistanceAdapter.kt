package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import kotlinx.android.synthetic.main.item_assistance.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class ShelterAssistanceAdapter(context: Context,
                               rowSaveListener: (ShelterAssistanceRow) -> Unit,
                               deleteClickListener: (ShelterAssistanceRow) -> Unit,
                               expandedItem: Int = 0) :
    BaseAssistanceAdapter<ShelterAssistanceRow, ShelterAssistanceAdapter.ViewHolder>
        (context, rowSaveListener, deleteClickListener, expandedItem = expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseAssistanceAdapter.ViewHolder<ShelterAssistanceRow>(itemView) {

        @SuppressLint("SetTextI18n")
        override fun populateWithDataInternal(row: ShelterAssistanceRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (ShelterAssistanceRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.text = "${view.resources.getString(R.string.assistance_item)} ${idx + 1}" }
            UIJobScheduler.submitJob { view.assistanceOrganizationText.text = row.organizationAgency }
            UIJobScheduler.submitJob { view.assistanceTypeText.text = row.assistanceType }
            UIJobScheduler.submitJob { view.assistanceDateReceivedText.date = row.dateReceived }
            UIJobScheduler.submitJob { view.assistanceQuantityText.text = row.quantity }
            UIJobScheduler.submitJob { view.assistanceMenText.number = row.beneficiariesMen }
            UIJobScheduler.submitJob { view.assistanceWomenText.number = row.beneficiariesWomen }
            UIJobScheduler.submitJob { view.assistanceBoysText.number = row.beneficiariesBoys }
            UIJobScheduler.submitJob { view.assistanceGirlsText.number = row.beneficiariesGirls }

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = ShelterAssistanceRow(
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