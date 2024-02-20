package com.noreplypratap.domain.di

import com.noreplypratap.domain.model.NotesUseCases
import com.noreplypratap.domain.repository.NotesRepository
import com.noreplypratap.domain.usecases.CreateNotesUseCase
import com.noreplypratap.domain.usecases.DeleteDatabaseUseCase
import com.noreplypratap.domain.usecases.DeleteNotesUseCase
import com.noreplypratap.domain.usecases.ReadNotesUseCase
import com.noreplypratap.domain.usecases.UpdateNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideNotesUseCase(notesRepository: NotesRepository): NotesUseCases {
        return NotesUseCases(
            createNotesUseCase = CreateNotesUseCase(notesRepository),
            readNotesUseCase = ReadNotesUseCase(notesRepository),
            updateNotesUseCase = UpdateNotesUseCase(notesRepository),
            deleteNotesUseCase = DeleteNotesUseCase(notesRepository),
            deleteDatabaseUseCase = DeleteDatabaseUseCase(notesRepository)
        )
    }
}