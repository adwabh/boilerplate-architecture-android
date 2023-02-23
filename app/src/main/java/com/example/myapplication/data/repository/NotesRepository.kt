package com.example.myapplication.data.repository

import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

interface NotesRepository {
    fun getNotes(index: Int, offset: Int): Flow<NotesResponse>
}

data class NotesResponse(
    val id: String,
    val title: String,
    val body: String,
    val type: String,
    val date: OffsetDateTime,
)
