package com.example.myapplication.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.core.database.dao.NotesDao
import com.example.myapplication.core.database.entities.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}