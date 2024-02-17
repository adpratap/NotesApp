package com.noreplypratap.domain.repository


import com.noreplypratap.domain.model.DomainNotes

interface NotesRepository {
    suspend fun createNotes(domainNotes: DomainNotes): Boolean
    suspend fun readNotes(): List<DomainNotes>
    suspend fun updateNotes(domainNotes: DomainNotes): Boolean
    suspend fun deleteNotes(domainNotes: DomainNotes): Boolean
    suspend fun deleteDatabase(): Boolean
}