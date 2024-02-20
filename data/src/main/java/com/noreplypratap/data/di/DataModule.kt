package com.noreplypratap.data.di

import android.app.Application
import androidx.room.Room
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
object DataModule {
    @Provides
    @Singleton
    fun provideNotesDao(notesDatabase: NotesDatabase) : NotesDao {
        return notesDatabase.notesDao
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesRepository(notesDao: NotesDao) : NotesRepository {
        return NotesRepositoryImpl(notesDao)
    }

}