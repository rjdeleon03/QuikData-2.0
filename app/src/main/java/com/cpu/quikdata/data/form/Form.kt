package com.cpu.quikdata.data.form

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude

@Entity(tableName = "form")
data class Form(@PrimaryKey(autoGenerate = false)
                val id: String,

                @get:Exclude
                var idRemote: String = "")