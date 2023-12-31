package com.artha.todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes where user_id = :userId")
    fun findNotes(userId: String) : Flow<List<NotesEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNotes(vararg notes: NotesEntity)
}