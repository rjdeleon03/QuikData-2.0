package com.cpu.quikdata.data.generalinfo.population.row

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cpu.quikdata.data.generalinfo.population.Population

@Entity(tableName = "population_row",
    indices = [Index("populationId")],
    foreignKeys = [ForeignKey(entity = Population::class,
        parentColumns = ["id"],
        childColumns = ["populationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class PopulationRow(@PrimaryKey(autoGenerate = false)
                         val id: String = "",
                         val type: Int = 0,
                         var affectedMale: Int = 0,
                         var affectedFemale: Int = 0,
                         var displacedMale: Int = 0,
                         var displacedFemale: Int = 0,
                         val populationId: String = "")