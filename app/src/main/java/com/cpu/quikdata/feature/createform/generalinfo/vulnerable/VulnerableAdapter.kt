package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
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
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.vulnerablePregnantText.number = row.pregnant }
            UIJobScheduler.submitJob { view.vulnerableLactatingText.number = row.lactating }
            UIJobScheduler.submitJob { view.vulnerableLgbtText.number = row.lgbt }
            UIJobScheduler.submitJob { view.vulnerableFemaleHeadedText.number = row.femaleHeaded }
            UIJobScheduler.submitJob { view.vulnerableChildHeadedText.number1 = row.childHeadedMale }
            UIJobScheduler.submitJob { view.vulnerableChildHeadedText.number2 = row.childHeadedFemale }
            UIJobScheduler.submitJob { view.vulnerableIndigenousText.number1 = row.indigenousMale }
            UIJobScheduler.submitJob { view.vulnerableIndigenousText.number2 = row.indigenousFemale }
            UIJobScheduler.submitJob { view.vulnerableDisabledText.number1 = row.disabledMale }
            UIJobScheduler.submitJob { view.vulnerableDisabledText.number2 = row.disabledFemale }
            UIJobScheduler.submitJob { view.vulnerableRemarksText.text = row.remarks }

            // Setup listener for saving each row
            UIJobScheduler.submitJob {
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
}