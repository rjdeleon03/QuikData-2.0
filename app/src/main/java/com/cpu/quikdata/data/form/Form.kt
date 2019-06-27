package com.cpu.quikdata.data.form

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cpu.quikdata.base.BaseDataWithId
import com.google.firebase.firestore.Exclude

@Entity(tableName = "form")
data class Form(@PrimaryKey(autoGenerate = false)
                override val id: String,

                @get:Exclude
                var isTemporary: Boolean = true,

                @get:Exclude
                var idRemote: String = "") : BaseDataWithId