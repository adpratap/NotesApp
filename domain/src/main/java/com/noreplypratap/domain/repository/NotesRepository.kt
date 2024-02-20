package com.noreplypratap.domain.repository


import com.noreplypratap.domain.model.DomainNotes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun createNotes(domainNotes: DomainNotes): Boolean
    fun readNotes(): Flow<List<DomainNotes>>
    suspend fun updateNotes(domainNotes: DomainNotes): Boolean
    suspend fun deleteNotes(domainNotes: DomainNotes): Boolean
    suspend fun deleteDatabase(): Boolean
}