package com.cpu.quikdata.data.generalinfo.population

import androidx.room.Embedded
import androidx.room.Relation
import com.cpu.quikdata.data.generalinfo.population.row.PopulationRow

class PopulationComplete {

    @Embedded
    var population: Population? = null

    @Relation(parentColumn = "id",
        entityColumn = "populationId",
        entity = PopulationRow::class)
    var populationRows: List<PopulationRow>? = null
}