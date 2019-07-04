package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import kotlinx.android.synthetic.main.item_vulnerable.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class VulnerableAdapter(context: Context, rowSaveListener: (VulnerableRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<VulnerableRow, VulnerableAdapter.ViewHolder>(context, R.layout.item_vulnerable, rowSaveListener, expandedItem) {

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<VulnerableRow>(itemView) {

        override fun populateWithDataInternal(row: VulnerableRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (VulnerableRow) -> Unit) {

            view.tag = idx
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

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
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