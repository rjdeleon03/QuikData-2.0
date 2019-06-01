package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import kotlinx.android.synthetic.main.item_diseases.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class DiseasesAdapter(context: Context, rowSaveListener: (DiseasesRow) -> Unit) :
    BaseAdapter<DiseasesRow, DiseasesAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_diseases, parent, false)
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

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<DiseasesRow>(itemView) {

        override fun populateWithData(row: DiseasesRow,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (DiseasesRow) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.id
            view.headerTextField.setText(AgeCategories.getStringId(row.type))
            view.diseasesDiarrheaText.number1 = row.diarrheaMale
            view.diseasesDiarrheaText.number2 = row.diarrheaFemale
            view.diseasesPneumoniaText.number1 = row.pneumoniaMale
            view.diseasesPneumoniaText.number2 = row.pneumoniaFemale
            view.diseasesDengueText.number1 = row.dengueMale
            view.diseasesDengueText.number2 = row.dengueFemale
            view.diseasesMeaslesText.number1 = row.measlesMale
            view.diseasesMeaslesText.number2 = row.measlesFemale
            view.diseasesOthersText.number1 = row.othersMale
            view.diseasesOthersText.number2 = row.othersFemale
            view.diseasesCheckUpText.number1 = row.checkUpMale
            view.diseasesCheckUpText.number2 = row.checkUpFemale
            view.diseasesHospitalizationText.number1 = row.hospitalizationMale
            view.diseasesHospitalizationText.number2 = row.hospitalizationFemale
            view.diseasesMedicinesText.number1 = row.medicinesMale
            view.diseasesMedicinesText.number2 = row.medicinesFemale
            view.diseasesMedicalOthersText.number1 = row.medicalOthersMale
            view.diseasesMedicalOthersText.number2 = row.medicalOthersFemale

            // Setup listener for saving each population row
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