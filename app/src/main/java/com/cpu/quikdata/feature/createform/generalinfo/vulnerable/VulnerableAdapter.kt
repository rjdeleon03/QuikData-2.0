package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import kotlinx.android.synthetic.main.item_vulnerable.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class VulnerableAdapter(context: Context, rowSaveListener: (VulnerableRow) -> Unit) :
    BaseAdapter<VulnerableRow, VulnerableAdapter.ViewHolder>(context, rowSaveListener) {

    private val mRowSaveListener = rowSaveListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_vulnerable, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, mRowSaveListener)
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<VulnerableRow>(itemView) {

        override fun populateWithData(row: VulnerableRow, rowSaveListener: (VulnerableRow) -> Unit) {
            view.tag = row.id
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.vulnerablePregnantText.number = row.pregnant
            view.vulnerableLactatingText.number = row.lactating
            view.vulnerableLgbtText.number = row.lgbt
            view.vulnerableFemaleHeadedText.number = row.femaleHeaded
            view.vulnerableChildHeadedText.number1 = row.childHeadedMale
            view.vulnerableChildHeadedText.number2 = row.childHeadedFemale
            view.vulnerableIndigenousText.number1 = row.indigenousMale
            view.vulnerableIndigenousText.number2 = row.indigenousFemale
            view.vulnerableDisabledText.number1 = row.disabledMale
            view.vulnerableDisabledText.number2 = row.disabledFemale
            view.vulnerableRemarksText.text = row.remarks

            // Setup listener for saving each population row
            (view as CollapsibleContainer).onDetachedListener = {
                val newRow = VulnerableRow(
                    row.id,
                    row.type,
                    view.vulnerablePregnantText.number,
                    view.vulnerableLactatingText.number,
                    view.vulnerableLgbtText.number,
                    view.vulnerableFemaleHeadedText.number,
                    view.vulnerableChildHeadedText.number1,
                    view.vulnerableChildHeadedText.number2,
                    view.vulnerableIndigenousText.number1,
                    view.vulnerableIndigenousText.number2,
                    view.vulnerableDisabledText.number1,
                    view.vulnerableDisabledText.number2,
                    view.vulnerableRemarksText.text,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }

        }
    }
}