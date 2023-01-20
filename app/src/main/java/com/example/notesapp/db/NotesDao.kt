package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.UserNotes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(userNotes: UserNotes)

    @Update
    suspend fun updateNote(userNote: UserNotes)

    @Query("SELECT * from notes_table")
    fun getNotes() : LiveData<List<UserNotes>>

    @Delete
    suspend fun deleteNote(userNote : UserNotes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

}