package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import kotlinx.android.synthetic.main.item_diseases.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class DiseasesAdapter(context: Context, rowSaveListener: (DiseasesRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<DiseasesRow, DiseasesAdapter.ViewHolder>(context, R.layout.item_diseases, rowSaveListener, expandedItem) {

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<DiseasesRow>(itemView) {

        override fun populateWithDataInternal(row: DiseasesRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (DiseasesRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(AgeCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.diseasesDiarrheaText.number1 = row.diarrheaMale }
            UIJobScheduler.submitJob { view.diseasesDiarrheaText.number2 = row.diarrheaFemale }
            UIJobScheduler.submitJob { view.diseasesPneumoniaText.number1 = row.pneumoniaMale }
            UIJobScheduler.submitJob { view.diseasesPneumoniaText.number2 = row.pneumoniaFemale }
            UIJobScheduler.submitJob { view.diseasesDengueText.number1 = row.dengueMale }
            UIJobScheduler.submitJob { view.diseasesDengueText.number2 = row.dengueFemale }
            UIJobScheduler.submitJob { view.diseasesMeaslesText.number1 = row.measlesMale }
            UIJobScheduler.submitJob { view.diseasesMeaslesText.number2 = row.measlesFemale }
            UIJobScheduler.submitJob { view.diseasesOthersText.number1 = row.othersMale }
            UIJobScheduler.submitJob { view.diseasesOthersText.number2 = row.othersFemale }
            UIJobScheduler.submitJob { view.diseasesCheckUpText.number1 = row.checkUpMale }
            UIJobScheduler.submitJob { view.diseasesCheckUpText.number2 = row.checkUpFemale }
            UIJobScheduler.submitJob { view.diseasesHospitalizationText.number1 = row.hospitalizationMale }
            UIJobScheduler.submitJob { view.diseasesHospitalizationText.number2 = row.hospitalizationFemale }
            UIJobScheduler.submitJob { view.diseasesMedicinesText.number1 = row.medicinesMale }
            UIJobScheduler.submitJob { view.diseasesMedicinesText.number2 = row.medicinesFemale }
            UIJobScheduler.submitJob { view.diseasesMedicalOthersText.number1 = row.medicalOthersMale }
            UIJobScheduler.submitJob { view.diseasesMedicalOthersText.number2 = row.medicalOthersFemale }

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = DiseasesRow(
                    row.id,
                    row.type,
                    view.diseasesDiarrheaText.number1,
                    view.diseasesDiarrheaText.number2,
                    view.diseasesPneumoniaText.number1,
                    view.diseasesPneumoniaText.number2,
                    view.diseasesDengueText.number1,
                    view.diseasesDengueText.number2,
                    view.diseasesMeaslesText.number1,
                    view.diseasesMeaslesText.number2,
                    view.diseasesOthersText.number1,
                    view.diseasesOthersText.number2,
                    view.diseasesCheckUpText.number1,
                    view.diseasesCheckUpText.number2,
                    view.diseasesHospitalizationText.number1,
                    view.diseasesHospitalizationText.number2,
                    view.diseasesMedicinesText.number1,
                    view.diseasesMedicinesText.number2,
                    view.diseasesMedicalOthersText.number1,
                    view.diseasesMedicalOthersText.number2,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}