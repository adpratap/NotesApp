package com.example.notesapp.di

import android.app.Application
import com.example.notesapp.db.NotesDao
import com.example.notesapp.db.NotesDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : NotesDatabase {
        return NotesDatabase.createDB(context)
    }

    @Provides
    @Singleton
    fun provideNotesDao(notesDatabase: NotesDatabase) : NotesDao {
        return notesDatabase.getNotesDao()
    }
}