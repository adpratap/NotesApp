package com.noreplypratap.domain.di

import com.noreplypratap.domain.repository.NotesRepository
import com.noreplypratap.domain.usecases.CreateNotesUseCase
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
object DomainModules {
    @Provides
    @Singleton
    fun provideCreateNotesUseCase(notesRepository: NotesRepository): CreateNotesUseCase = CreateNotesUseCase(notesRepository)


    @Provides
    @Singleton
    fun provideReadNotesUseCase(notesRepository: NotesRepository): ReadNotesUseCase = ReadNotesUseCase(notesRepository)

    @Provides
    @Singleton
    fun provideUpdateNotesUseCase(notesRepository: NotesRepository): UpdateNotesUseCase = UpdateNotesUseCase(notesRepository)

    @Provides
    @Singleton
    fun provideDeleteNotesUseCase(notesRepository: NotesRepository): DeleteNotesUseCase = DeleteNotesUseCase(notesRepository)

}