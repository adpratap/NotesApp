package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.model.UserNotes

@Database(entities = [UserNotes::class], version = 1 , exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getNotesDao() : NotesDao

    companion object{
        @Volatile
        private var instance : NotesDatabase? = null

        fun createDB(context: Context) : NotesDatabase {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "NotesDatabase"
                    ).build()
                }
            }
            return instance!!
        }
    }

}