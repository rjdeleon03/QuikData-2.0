package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.di.annotation.ContextFromFragment
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_population.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PopulationAdapter @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted rowSaveListener: (PopulationRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<PopulationRow, PopulationAdapter.ViewHolder>(context, R.layout.item_population, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(context: Context, rowSaveListener: (PopulationRow) -> Unit, expandedItem: Int): PopulationAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<PopulationRow>(itemView) {

        override fun populateWithDataInternal(row: PopulationRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (PopulationRow) -> Unit) {

            view.tag = idx
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.populationAffectedText.number1 = row.affectedMale
            view.populationAffectedText.number2 = row.affectedFemale
            view.populationDisplacedText.number1 = row.displacedMale
            view.populationDisplacedText.number2 = row.displacedFemale

            // Setup listener for saving each row
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