package com.example.myapplication.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    val type: String,
    val date: Long,
    @ColumnInfo(name = "user_id") val userId: String
)