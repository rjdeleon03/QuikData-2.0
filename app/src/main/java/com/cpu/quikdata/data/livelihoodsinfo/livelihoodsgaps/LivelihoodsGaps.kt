package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.form.Form

@Entity(tableName = "livelihoods_gaps",
    indices = [Index("formId")],
    foreignKeys = [ForeignKey(entity = Form::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )])
data class LivelihoodsGaps(@PrimaryKey(autoGenerate = false)
                           val id: String = "",
                           var hasLocalMarket: Boolean = false,
                           var hasLocalMarketRemarks: String = "",
                           var hasCashOpportunities: Boolean = false,
                           var hasCashOpportunitiesRemarks: String = "",
                           var hasCredit: Boolean = false,
                           var hasCreditRemarks: String = "",
                           var hasLivelihoodMaterials: Boolean = false,
                           var hasLivelihoodMaterialsRemarks: String = "",
                           var formIdRemote: String = "",
                           val formId: String = "") {

    fun copyFrom(livelihoodsGaps: LivelihoodsGaps) {
        hasLocalMarket = livelihoodsGaps.hasLocalMarket
        hasLocalMarketRemarks = livelihoodsGaps.hasLocalMarketRemarks
        hasCashOpportunities = livelihoodsGaps.hasCashOpportunities
        hasCashOpportunitiesRemarks = livelihoodsGaps.hasCashOpportunitiesRemarks
        hasCredit = livelihoodsGaps.hasCredit
        hasCreditRemarks = livelihoodsGaps.hasCreditRemarks
        hasLivelihoodMaterials = livelihoodsGaps.hasLivelihoodMaterials
        hasLivelihoodMaterialsRemarks = livelihoodsGaps.hasLivelihoodMaterialsRemarks
        formIdRemote = livelihoodsGaps.formIdRemote
    }
}