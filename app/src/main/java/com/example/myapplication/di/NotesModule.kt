package com.example.myapplication.di

import com.example.myapplication.data.datasource.OfflineFirstNotesDataSource
import com.example.myapplication.data.repository.NotesRepository
import com.example.myapplication.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NotesModule {

    @Binds
    abstract fun bindRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository
}