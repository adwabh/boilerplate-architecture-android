package com.artha.todo.di

import com.artha.todo.data.repo.NotesRepository
import com.artha.todo.data.repo.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsNotesRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ) : NotesRepository
}