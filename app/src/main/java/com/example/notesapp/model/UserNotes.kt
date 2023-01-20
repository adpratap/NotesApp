package com.example.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class UserNotes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String?,
    val body : String?,
    val Date : String?
)