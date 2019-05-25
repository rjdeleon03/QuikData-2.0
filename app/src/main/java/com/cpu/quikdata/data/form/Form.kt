package com.cpu.quikdata.data.form

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form")
data class Form(@PrimaryKey(autoGenerate = false)
                val id: String)