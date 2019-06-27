package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import kotlinx.android.synthetic.main.item_cause_of_death.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class CauseOfDeathAdapter(context: Context, rowSaveListener: (CauseOfDeathRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<CauseOfDeathRow, CauseOfDeathAdapter.ViewHolder>(context, R.layout.item_cause_of_death, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<CauseOfDeathRow>(itemView) {

        override fun populateWithDataInternal(row: CauseOfDeathRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (CauseOfDeathRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.causeOfDeathMeaslesText.number1 = row.measlesMale }
            UIJobScheduler.submitJob { view.causeOfDeathMeaslesText.number2 = row.measlesFemale }
            UIJobScheduler.submitJob { view.causeOfDeathDiarrheaText.number1 = row.diarrheaMale }
            UIJobScheduler.submitJob { view.causeOfDeathDiarrheaText.number2 = row.diarrheaFemale }
            UIJobScheduler.submitJob { view.causeOfDeathPneumoniaText.number1 = row.pneumoniaMale }
            UIJobScheduler.submitJob { view.causeOfDeathPneumoniaText.number2 = row.pneumoniaFemale }
            UIJobScheduler.submitJob { view.causeOfDeathDengueText.number1 = row.dengueMale }
            UIJobScheduler.submitJob { view.causeOfDeathDengueText.number2 = row.dengueFemale }
            UIJobScheduler.submitJob { view.causeOfDeathDrowningText.number1 = row.drowningMale }
            UIJobScheduler.submitJob { view.causeOfDeathDrowningText.number2 = row.drowningFemale }
            UIJobScheduler.submitJob { view.causeOfDeathHeartAttackText.number1 = row.heartAttackMale }
            UIJobScheduler.submitJob { view.causeOfDeathHeartAttackText.number2 = row.heartAttackFemale }
            UIJobScheduler.submitJob { view.causeOfDeathElectrocutionText.number1 = row.electrocutionMale }
            UIJobScheduler.submitJob { view.causeOfDeathElectrocutionText.number2 = row.electrocutionFemale }
            UIJobScheduler.submitJob { view.causeOfDeathCollapsedBldgText.number1 = row.collapsedBldgMale }
            UIJobScheduler.submitJob { view.causeOfDeathCollapsedBldgText.number2 = row.collapsedBldgFemale }
            UIJobScheduler.submitJob { view.causeOfDeathOthersText.number1 = row.othersMale }
            UIJobScheduler.submitJob { view.causeOfDeathOthersText.number2 = row.othersFemale }

            // Setup listener for saving each row
            UIJobScheduler.submitJob {
                collapsibleView?.onDetachedListener = {
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
}