package com.noreplypratap.domain.usecases

import com.noreplypratap.domain.model.DomainNotes
import com.noreplypratap.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ReadNotesUseCase(
  private val notesRepository: NotesRepository
) {
  operator fun invoke(): Flow<List<DomainNotes>> = flow {
    emit(notesRepository.readNotes())
  }
}
