package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import android.content.Context
import android.view.View
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.common.HouseCategories
import com.cpu.quikdata.common.UIJobScheduler
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import kotlinx.android.synthetic.main.item_house_damage.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class HouseDamageAdapter(context: Context, rowSaveListener: (HouseDamageRow) -> Unit, expandedItem: Int = 0) :
    BaseCollapsibleAdapter<HouseDamageRow, HouseDamageAdapter.ViewHolder>(context, R.layout.item_house_damage, rowSaveListener, expandedItem) {

    override fun createViewHolder(view: View): ViewHolder = ViewHolder(view)

    class ViewHolder(itemView: View) : BaseCollapsibleAdapter.ViewHolder<HouseDamageRow>(itemView) {

        override fun populateWithDataInternal(row: HouseDamageRow,
                                              idx: Int,
                                              isCollapsed: Boolean,
                                              rowSaveListener: (HouseDamageRow) -> Unit) {

            view.tag = idx
            UIJobScheduler.submitJob { view.headerTextField.setText(HouseCategories.getStringId(row.type)) }
            UIJobScheduler.submitJob { view.houseDamageOwnedHouseholdsText.number = row.ownedHouseholds }
            UIJobScheduler.submitJob { view.houseDamageRentedHouseholdsText.number = row.rentedHouseholds }
            UIJobScheduler.submitJob { view.houseDamageSharedHouseholdsText.number = row.sharedHouseholds }
            UIJobScheduler.submitJob { view.houseDamageOwnedLandText.number = row.ownedLand }
            UIJobScheduler.submitJob { view.houseDamageRentedLandText.number = row.rentedLand }
            UIJobScheduler.submitJob { view.houseDamageTenantedLandText.number = row.tenantedLand }
            UIJobScheduler.submitJob { view.houseDamageInformalSettlersText.number = row.informalSettlers }
            UIJobScheduler.submitJob { view.houseDamagePartiallyDamagedText.number = row.partiallyDamaged }
            UIJobScheduler.submitJob { view.houseDamageTotallyDamagedText.number = row.totallyDamaged }

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