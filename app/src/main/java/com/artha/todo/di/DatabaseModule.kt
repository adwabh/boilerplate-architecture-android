package com.artha.todo.di

import android.content.Context
import androidx.room.Room
import com.artha.todo.data.NotesDao
import com.artha.todo.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesNotesDatabase(@ApplicationContext appContext: Context): NotesDatabase = Room.databaseBuilder(
        appContext,
        NotesDatabase::class.java,
        "notes_db"
    ).build()

    @Provides
    fun providesNotesDao(notesDatabase: NotesDatabase): NotesDao = notesDatabase.notesDao()
}