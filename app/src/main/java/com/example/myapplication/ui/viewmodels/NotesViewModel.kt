package com.example.myapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.myapplication.example.core.data.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(notesRepository: NotesRepository) : ViewModel() {
//    val stateFlow: StateFlow<NotesState> = notesRepository.notesState
}

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

class NotesState {

}
