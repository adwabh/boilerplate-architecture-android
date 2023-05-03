package com.example.myapplication.di

import com.myapplication.example.core.data.notes.NotesRepository
import com.myapplication.example.core.data.notes.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NotesModule {

    @Binds
    abstract fun bindRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository
}