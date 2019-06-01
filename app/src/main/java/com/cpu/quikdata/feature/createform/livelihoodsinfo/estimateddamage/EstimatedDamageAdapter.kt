package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseAdapter
import com.cpu.quikdata.common.LivelihoodCategories
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageRow
import kotlinx.android.synthetic.main.item_estimated_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class EstimatedDamageAdapter(context: Context, rowSaveListener: (EstimatedDamageComplete) -> Unit) :
    BaseAdapter<EstimatedDamageComplete, EstimatedDamageAdapter.ViewHolder>(context, rowSaveListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_estimated_damage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, row.row!!.type,
            mExpandedItem != row.row!!.type, mRowSaveListener,
            { idx, isCollapsed ->
                if (!isCollapsed) mExpandedItem = idx
            })
    }

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<EstimatedDamageComplete>(itemView) {

        override fun populateWithData(row: EstimatedDamageComplete,
                                      isCollapsed: Boolean,
                                      rowSaveListener: (EstimatedDamageComplete) -> Unit,
                                      rowCollapsedStateChangedListener: (Int, Boolean) -> Unit) {

            view.tag = row.row!!.id
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
                if (row.row!! != newRow) {
                    row.row = newRow
                    rowSaveListener(row)
                }
            }
        }
    }
}