package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import kotlinx.android.synthetic.main.item_cause_of_death.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class CauseOfDeathAdapter(context: Context, rowSaveListener: (CauseOfDeathRow) -> Unit) :
    BaseAdapter<CauseOfDeathRow, CauseOfDeathAdapter.ViewHolder>(context, rowSaveListener) {

    private val mRowSaveListener = rowSaveListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_cause_of_death, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, mRowSaveListener)
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<CauseOfDeathRow>(itemView) {

        override fun populateWithData(row: CauseOfDeathRow, rowSaveListener: (CauseOfDeathRow) -> Unit) {
            view.tag = row.id
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.causeOfDeathMeaslesText.number1 = row.measlesMale
            view.causeOfDeathMeaslesText.number2 = row.measlesFemale
            view.causeOfDeathDiarrheaText.number1 = row.diarrheaMale
            view.causeOfDeathDiarrheaText.number2 = row.diarrheaFemale
            view.causeOfDeathPneumoniaText.number1 = row.pneumoniaMale
            view.causeOfDeathPneumoniaText.number2 = row.pneumoniaFemale
            view.causeOfDeathDengueText.number1 = row.dengueMale
            view.causeOfDeathDengueText.number2 = row.dengueFemale
            view.causeOfDeathDrowningText.number1 = row.drowningMale
            view.causeOfDeathDrowningText.number2 = row.drowningFemale
            view.causeOfDeathHeartAttackText.number1 = row.heartAttackMale
            view.causeOfDeathHeartAttackText.number2 = row.heartAttackFemale
            view.causeOfDeathElectrocutionText.number1 = row.electrocutionMale
            view.causeOfDeathElectrocutionText.number2 = row.electrocutionFemale
            view.causeOfDeathCollapsedBldgText.number1 = row.collapsedBldgMale
            view.causeOfDeathCollapsedBldgText.number2 = row.collapsedBldgFemale
            view.causeOfDeathOthersText.number1 = row.othersMale
            view.causeOfDeathOthersText.number2 = row.othersFemale

            // Setup listener for saving each vulnerable population row
            (view as CollapsibleContainer).onDetachedListener = {
                val newRow = CauseOfDeathRow(
                    row.id,
                    row.type,
                    view.causeOfDeathMeaslesText.number1,
                    view.causeOfDeathMeaslesText.number2,
                    view.causeOfDeathDiarrheaText.number1,
                    view.causeOfDeathDiarrheaText.number2,
                    view.causeOfDeathPneumoniaText.number1,
                    view.causeOfDeathPneumoniaText.number2,
                    view.causeOfDeathDengueText.number1,
                    view.causeOfDeathDengueText.number2,
                    view.causeOfDeathDrowningText.number1,
                    view.causeOfDeathDrowningText.number2,
                    view.causeOfDeathHeartAttackText.number1,
                    view.causeOfDeathHeartAttackText.number2,
                    view.causeOfDeathElectrocutionText.number1,
                    view.causeOfDeathElectrocutionText.number2,
                    view.causeOfDeathCollapsedBldgText.number1,
                    view.causeOfDeathCollapsedBldgText.number2,
                    view.causeOfDeathOthersText.number1,
                    view.causeOfDeathOthersText.number2,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }

        }
    }
}