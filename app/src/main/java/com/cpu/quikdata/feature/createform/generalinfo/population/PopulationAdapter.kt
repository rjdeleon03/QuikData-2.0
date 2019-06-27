package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import kotlinx.android.synthetic.main.item_population.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PopulationAdapter(context: Context, rowSaveListener: (PopulationRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<PopulationRow, PopulationAdapter.ViewHolder>(context, R.layout.item_population, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<PopulationRow>(itemView) {

        override fun populateWithDataInternal(row: PopulationRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (PopulationRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.populationAffectedText.number1 = row.affectedMale }
            UIJobScheduler.submitJob { view.populationAffectedText.number2 = row.affectedFemale }
            UIJobScheduler.submitJob { view.populationDisplacedText.number1 = row.displacedMale }
            UIJobScheduler.submitJob { view.populationDisplacedText.number2 = row.displacedFemale }

            // Setup listener for saving each row
            UIJobScheduler.submitJob {
                collapsibleView?.onDetachedListener = {
                    val newRow = PopulationRow(
                        row.id,
                        row.type,
                        view.populationAffectedText.number1,
                        view.populationAffectedText.number2,
                        view.populationDisplacedText.number1,
                        view.populationDisplacedText.number2,
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