package com.noreplypratap.data.di

import android.app.Application
import com.noreplypratap.data.repository.NotesRepositoryImpl
import com.noreplypratap.data.source.NotesDao
import com.noreplypratap.data.source.NotesDatabase
import com.noreplypratap.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {
    @Provides
    @Singleton
    fun provideNotesDao(notesDatabase: NotesDatabase) : NotesDao {
        return notesDatabase.getNotesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : NotesDatabase {
        return NotesDatabase.createDB(context)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(notesDao: NotesDao) : NotesRepository {
        return NotesRepositoryImpl(notesDao)
    }


}