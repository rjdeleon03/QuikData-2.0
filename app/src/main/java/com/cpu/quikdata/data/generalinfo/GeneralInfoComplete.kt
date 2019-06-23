package com.cpu.quikdata.data.generalinfo

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow

class GeneralInfoComplete {

    @Embedded
    var form: Form? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = CalamityInfo::class)
    var calamityInfo: List<CalamityInfo>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = CasualtiesRow::class)
    var casualties: List<CasualtiesRow>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = CauseOfDeathRow::class)
    var causeOfDeath: List<CauseOfDeathRow>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = Families::class)
    var families: List<Families>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = InfrastructureDamageRow::class)
    var infrastructureDamage: List<InfrastructureDamageRow>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = PopulationRow::class)
    var population: List<PopulationRow>? = null

    @Relation(parentColumn = "id",
        entityColumn = "formId",
        entity = VulnerableRow::class)
    var vulnerable: List<VulnerableRow>? = null
}