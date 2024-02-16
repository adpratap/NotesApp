package com.noreplypratap.domain.usecases

import com.noreplypratap.domain.model.DomainNotes
import com.noreplypratap.domain.repository.NotesRepository


class CreateNotesUseCase(
  private val notesRepository: NotesRepository
) {
  suspend operator fun invoke(domainNotes: DomainNotes) = notesRepository.createNotes(domainNotes)
}
