package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.base.BaseAssistanceAdapter
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import kotlinx.android.synthetic.main.item_assistance.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class FoodSecurityAssistanceAdapter(context: Context, rowSaveListener: (FoodSecurityAssistanceRow) -> Unit) :
    BaseAssistanceAdapter<FoodSecurityAssistanceRow, FoodSecurityAssistanceAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_assistance, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.index = position
        holder.populateWithData(row!!, position,
            mExpandedItem != position, mRowSaveListener,
            { idx, isCollapsed ->
                if (!isCollapsed) mExpandedItem = idx
            })
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<FoodSecurityAssistanceRow>(itemView) {

        private var mIndex = 0

        var index: Int = 0
            set(value) {
                mIndex = value
                field = value
            }

        @SuppressLint("SetTextI18n")
        override fun populateWithData(row: FoodSecurityAssistanceRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (FoodSecurityAssistanceRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.text = "${view.resources.getString(R.string.assistance_item)} ${mIndex + 1}"
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
                val newRow = FoodSecurityAssistanceRow(
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