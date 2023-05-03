package com.myapplication.example.core.data.notes

import com.myapplication.example.core.data.notes.model.NoteData
import com.myapplication.example.core.data.notes.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesRepository {
    fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>>
}

class NotesRepositoryImpl @Inject constructor(): NotesRepository {

    /*private var state: StateFlow<NotesState> = MutableStateFlow(NotesState())
    override val notesState: StateFlow<NotesState>
        get() = state

    override suspend fun getNotes(scope: CoroutineScope, index: Int, offset: Int): StateFlow<NotesResponse> {

        val stateIn = flowOf(
            NotesResponse(
                "",
                "",
                "",
                "",
                OffsetDateTime.now()
            )
        )
        state = stateIn.map { res ->
            NotesState()
        }.stateIn(scope)
        return stateIn.stateIn(scope)
    }*/
    override fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>> {

    }

}
