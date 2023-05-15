package com.example.myapplication.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.core.database.entities.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes where user_id = :userId")
    fun findNotes(userId: String) : Flow<List<NotesEntity>>

    @Insert
    fun updateNotes(vararg notes: NotesEntity)
}