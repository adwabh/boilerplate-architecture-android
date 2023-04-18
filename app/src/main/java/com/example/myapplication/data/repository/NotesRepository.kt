package com.example.myapplication.data.repository

import com.example.myapplication.ui.viewmodels.NotesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import java.time.OffsetDateTime

interface NotesRepository {
    val notesState: StateFlow<NotesState>

    suspend fun getNotes(scope: CoroutineScope, index: Int, offset: Int): StateFlow<NotesResponse>
}

data class NotesResponse(
    val id: String,
    val title: String,
    val body: String,
    val type: String,
    val date: OffsetDateTime,
)
