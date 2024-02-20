package com.noreplypratap.domain.model

import com.noreplypratap.domain.usecases.CreateNotesUseCase
import com.noreplypratap.domain.usecases.DeleteDatabaseUseCase
import com.noreplypratap.domain.usecases.DeleteNotesUseCase
import com.noreplypratap.domain.usecases.ReadNotesUseCase
import com.noreplypratap.domain.usecases.UpdateNotesUseCase

data class NotesUseCases(
    val createNotesUseCase: CreateNotesUseCase,
    val readNotesUseCase: ReadNotesUseCase,
    val updateNotesUseCase: UpdateNotesUseCase,
    val deleteNotesUseCase: DeleteNotesUseCase,
    val deleteDatabaseUseCase: DeleteDatabaseUseCase
)
