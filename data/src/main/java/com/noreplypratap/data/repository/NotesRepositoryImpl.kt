package com.noreplypratap.data.repository

import com.noreplypratap.data.mappers.toDataNotes
import com.noreplypratap.data.source.NotesDao
import com.noreplypratap.domain.model.DomainNotes
import com.noreplypratap.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {
    override suspend fun createNotes(domainNotes: DomainNotes): Boolean {
        notesDao.saveNote(domainNotes.toDataNotes())
        return true
    }

    override fun readNotes(): Flow<List<DomainNotes>> {
        return notesDao.getNotes()
    }

    override suspend fun updateNotes(domainNotes: DomainNotes): Boolean {
        notesDao.updateNote(domainNotes.toDataNotes())
        return true
    }

    override suspend fun deleteNotes(domainNotes: DomainNotes): Boolean {
        notesDao.deleteNote(domainNotes.toDataNotes())
        return true
    }

    override suspend fun deleteDatabase(): Boolean {
        notesDao.deleteDatabase()
        return true
    }
}