package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import kotlinx.android.synthetic.main.item_population.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PopulationAdapter(context: Context, rowSaveListener: (PopulationRow) -> Unit) :
    BaseAdapter<PopulationRow, PopulationAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_population, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, row.type,
            mExpandedItem != row.type, mRowSaveListener,
            { idx, isCollapsed ->
                if (!isCollapsed) mExpandedItem = idx
            })
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<PopulationRow>(itemView) {

        override fun populateWithData(row: PopulationRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (PopulationRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.populationAffectedText.number1 = row.affectedMale
            view.populationAffectedText.number2 = row.affectedFemale
            view.populationDisplacedText.number1 = row.displacedMale
            view.populationDisplacedText.number2 = row.displacedFemale

            // Setup listener for saving each population row
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