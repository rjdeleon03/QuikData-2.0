package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.HouseCategories
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.android.synthetic.main.item_house_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class HouseDamageAdapter @AssistedInject constructor (
    context: Context,
    @Assisted rowSaveListener: (HouseDamageRow) -> Unit,
    @Assisted expandedItem: Int = 0) :
    BaseCollapsibleAdapter<HouseDamageRow, HouseDamageAdapter.ViewHolder>(context, R.layout.item_house_damage, rowSaveListener, expandedItem) {

    @AssistedInject.Factory
    interface Factory {
        fun create(rowSaveListener: (HouseDamageRow) -> Unit, expandedItem: Int): HouseDamageAdapter
    }

    override fun initCollapsibleViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<HouseDamageRow>(itemView) {

        override fun populateWithDataInternal(row: HouseDamageRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (HouseDamageRow) -> Unit) {

            view.tag = idx
            view.headerTextField.setText(HouseCategories.getStringId(row.type))
            view.houseDamageOwnedHouseholdsText.number = row.ownedHouseholds
            view.houseDamageRentedHouseholdsText.number = row.rentedHouseholds
            view.houseDamageSharedHouseholdsText.number = row.sharedHouseholds
            view.houseDamageOwnedLandText.number = row.ownedLand
            view.houseDamageRentedLandText.number = row.rentedLand
            view.houseDamageTenantedLandText.number = row.tenantedLand
            view.houseDamageInformalSettlersText.number = row.informalSettlers
            view.houseDamagePartiallyDamagedText.number = row.partiallyDamaged
            view.houseDamageTotallyDamagedText.number = row.totallyDamaged

            // Setup listener for saving each row
            collapsibleView?.onDetachedListener = {
                val newRow = HouseDamageRow(
                    row.id,
                    row.type,
                    view.houseDamageOwnedHouseholdsText.number,
                    view.houseDamageRentedHouseholdsText.number,
                    view.houseDamageSharedHouseholdsText.number,
                    view.houseDamageOwnedLandText.number,
                    view.houseDamageRentedLandText.number,
                    view.houseDamageTenantedLandText.number,
                    view.houseDamageInformalSettlersText.number,
                    view.houseDamagePartiallyDamagedText.number,
                    view.houseDamageTotallyDamagedText.number,
                    row.formId
                )
                if (row != newRow) {
                    rowSaveListener(newRow)
                }
            }
        }
    }
}