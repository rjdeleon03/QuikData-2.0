package com.cpu.quikdata.data.form

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude

@Entity(tableName = "form")
data class Form(@PrimaryKey(autoGenerate = false)
                val id: String,

                val dateCreated: Long = 0L,

                var dateModified: Long = 0L,

                @get:Exclude
                var isTemporary: Boolean = true,

                @get:Exclude
                var idRemote: String = "")