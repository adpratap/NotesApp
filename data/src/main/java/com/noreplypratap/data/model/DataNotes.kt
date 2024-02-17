package com.noreplypratap.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class DataNotes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String?,
    val body : String?,
    val date : String?
)