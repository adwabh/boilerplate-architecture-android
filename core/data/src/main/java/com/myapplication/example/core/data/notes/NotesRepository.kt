package com.myapplication.example.core.data.notes

import com.myapplication.example.core.data.notes.model.NoteData
import com.myapplication.example.core.data.notes.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.time.OffsetDateTime
import javax.inject.Inject


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

interface NotesRepository {
    fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>>
}

class NotesRepositoryImpl @Inject constructor(): NotesRepository {
    override fun getNotes(user: User): Flow<List<NoteData>> {
        TODO("Not yet implemented")
    }

}
