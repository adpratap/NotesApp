package com.noreplypratap.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noreplypratap.data.model.DataNotes

@Database(entities = [DataNotes::class], version = 1 , exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao : NotesDao
    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}