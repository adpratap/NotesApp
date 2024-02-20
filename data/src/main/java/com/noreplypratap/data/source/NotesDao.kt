package com.noreplypratap.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.noreplypratap.data.model.DataNotes
import com.noreplypratap.domain.model.DomainNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(dataNotes: DataNotes)

    @Update
    suspend fun updateNote(dataNotes: DataNotes)

    @Query("SELECT * from notes_table")
    fun getNotes() : Flow<List<DomainNotes>>

    @Delete
    suspend fun deleteNote(dataNotes: DataNotes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteDatabase()

}