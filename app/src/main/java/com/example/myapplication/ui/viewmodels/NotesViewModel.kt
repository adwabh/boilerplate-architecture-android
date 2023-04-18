package com.example.myapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(notesRepository: NotesRepository) : ViewModel() {
    val stateFlow: StateFlow<NotesState> = notesRepository.notesState
}

class NotesState {

}
