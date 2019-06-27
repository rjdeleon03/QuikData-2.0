package com.cpu.quikdata.feature.createform.generalinfo.casualties

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import kotlinx.android.synthetic.main.item_casualties.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class CasualtiesAdapter(context: Context, rowSaveListener: (CasualtiesRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<CasualtiesRow, CasualtiesAdapter.ViewHolder>(context, R.layout.item_casualties, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<CasualtiesRow>(itemView) {

        override fun populateWithDataInternal(row: CasualtiesRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (CasualtiesRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.casualtiesDeadText.number1 = row.deadMale }
            UIJobScheduler.submitJob { view.casualtiesDeadText.number2 = row.deadFemale }
            UIJobScheduler.submitJob { view.casualtiesMissingText.number1 = row.missingMale }
            UIJobScheduler.submitJob { view.casualtiesMissingText.number2 = row.missingFemale }
            UIJobScheduler.submitJob { view.casualtiesInjuredText.number1 = row.injuredMale }
            UIJobScheduler.submitJob { view.casualtiesInjuredText.number2 = row.injuredFemale }

            // Setup listener for saving each row
            UIJobScheduler.submitJob {
                collapsibleView?.onDetachedListener = {
                    val newRow = CasualtiesRow(
                        row.id,
                        row.type,
                        view.casualtiesDeadText.number1,
                        view.casualtiesDeadText.number2,
                        view.casualtiesMissingText.number1,
                        view.casualtiesMissingText.number2,
                        view.casualtiesInjuredText.number1,
                        view.casualtiesInjuredText.number2,
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