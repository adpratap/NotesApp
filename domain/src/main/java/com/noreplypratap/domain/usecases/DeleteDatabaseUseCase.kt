package com.noreplypratap.domain.usecases

import com.noreplypratap.domain.repository.NotesRepository


class DeleteDatabaseUseCase(
  private val notesRepository: NotesRepository
) {
  suspend operator fun invoke() = notesRepository.deleteDatabase()
}
