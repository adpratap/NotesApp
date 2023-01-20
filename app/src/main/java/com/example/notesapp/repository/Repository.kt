package com.example.notesapp.repository

import com.example.notesapp.db.NotesDao
import com.example.notesapp.model.UserNotes
import javax.inject.Inject

class Repository @Inject constructor(private val notesDao: NotesDao) {

    fun getNotes() = notesDao.getNotes()

    suspend fun saveNote(note: UserNotes){
        notesDao.saveNote(note)
    }

    suspend fun updateNote(note: UserNotes){
        notesDao.updateNote(note)
    }

    suspend fun deleteAllNotes() = notesDao.deleteAll()

    suspend fun deleteNote(note: UserNotes) {
        notesDao.deleteNote(note)
    }

}