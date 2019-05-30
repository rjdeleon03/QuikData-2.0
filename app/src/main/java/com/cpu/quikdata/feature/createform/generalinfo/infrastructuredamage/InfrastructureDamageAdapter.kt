package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.InfraCategories
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class InfrastructureDamageAdapter(context: Context, rowSaveListener: (InfrastructureDamageRow) -> Unit) :
    BaseAdapter<InfrastructureDamageRow, InfrastructureDamageAdapter.ViewHolder>(context, rowSaveListener) {

    private val mRowSaveListener = rowSaveListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_infrastructure_damage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, mRowSaveListener)
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<InfrastructureDamageRow>(itemView) {

        override fun populateWithData(row: InfrastructureDamageRow, rowSaveListener: (InfrastructureDamageRow) -> Unit) {
            view.tag = row.id
            view.headerTextField.setText(InfraCategories.getStringId(row.type))
//            view.populationAffectedText.number1 = row.affectedMale
//            view.populationAffectedText.number2 = row.affectedFemale
//            view.populationDisplacedText.number1 = row.displacedMale
//            view.populationDisplacedText.number2 = row.displacedFemale

            // Setup listener for saving each population row
            (view as CollapsibleContainer).onDetachedListener = {
//                val newRow = InfrastructureDamageRow(
//                    row.id,
//                    row.type,
//                    view.populationAffectedText.number1,
//                    view.populationAffectedText.number2,
//                    view.populationDisplacedText.number1,
//                    view.populationDisplacedText.number2,
//                    row.formId
//                )
//                if (row != newRow) {
//                    rowSaveListener(newRow)
//                }
            }

        }
    }
}