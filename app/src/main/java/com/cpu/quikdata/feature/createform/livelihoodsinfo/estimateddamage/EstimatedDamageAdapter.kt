package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.LivelihoodCategories
import com.cpu.quikdata.common.LivelihoodSubcategories
import com.cpu.quikdata.customviews.questions.MultipleChoiceQuestion
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_estimated_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class EstimatedDamageAdapter @AssistedInject constructor (
    context: Context,
    @Assisted rowSaveListener: (EstimatedDamageComplete) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<EstimatedDamageComplete, EstimatedDamageAdapter.ViewHolder>(context, R.layout.item_estimated_damage, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (EstimatedDamageComplete) -> Unit, expandedItem: Int): EstimatedDamageAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<EstimatedDamageComplete>(itemView) {

        override fun populateWithDataInternal(row: EstimatedDamageComplete,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (EstimatedDamageComplete) -> Unit) {

            val multipleChoiceView = view.findViewById<MultipleChoiceQuestion>(R.id.estimatedDamageKindsText)
            multipleChoiceView.clear()
            for (item in row.types!!) {
                multipleChoiceView.addItem(LivelihoodSubcategories.getStringId(item.type), item.isSelected)
            }

            view.tag = idx
            view.headerTextField.setText(LivelihoodCategories.getStringId(row.row!!.type))
            view.estimatedDamageCostText.number = row.row!!.damageCost
            view.estimatedDamageRemarksText.text = row.row!!.remarks

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = EstimatedDamageRow(
                    row.row!!.id,
                    row.row!!.type,
                    view.estimatedDamageCostText.number,
                    view.estimatedDamageRemarksText.text,
                    row.row!!.formId
                )

                val multipleChoiceItems = multipleChoiceView.getItems()
                var didUpdateChoices = false
                for(i in 0 until multipleChoiceItems.size) {
                    if (row.types!![i].isSelected != multipleChoiceItems[i]) {
                        didUpdateChoices = true
                        row.types!![i].isSelected = multipleChoiceItems[i]
                    }
                }

                if (row.row!! != newRow || didUpdateChoices) {
                    row.row = newRow
                    rowSaveListener(row)
                }
            }
        }
    }
}