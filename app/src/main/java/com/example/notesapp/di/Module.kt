package com.example.notesapp.di

import android.content.Context
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideContext(@ApplicationContext application: Context): Context {
        return application
    }

}