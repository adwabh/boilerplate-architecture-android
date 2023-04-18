package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.OfflineFirstNotesDataSource
import com.example.myapplication.ui.viewmodels.NotesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.OffsetDateTime
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(val dataSource: OfflineFirstNotesDataSource) : NotesRepository {
    private var state: StateFlow<NotesState> = MutableStateFlow(NotesState())
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
    }


}