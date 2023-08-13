package com.artha.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artha.todo.data.NoteData
import com.artha.todo.data.UserPreference
import com.artha.todo.data.repo.NotesRepository
import com.artha.todo.network.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import  com.artha.todo.network.Result

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    val state: StateFlow<HomeState>
        get() {
            return repository.getNotes(userPreference.getCurrentUser(), 0,0)
                .asResult()
                .map(::mapToState)
                .stateIn(viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = HomeState.LOADING
                )
        }
    private fun mapToState(notes: Result<List<NoteData>>): HomeState = when(notes) {
        is Result.Success -> {
            HomeState.SUCCESS(notes.data)
        }
        is Result.Error -> {
            HomeState.ERROR
        }
        is Result.Loading -> {
            HomeState.LOADING
        }
    }
}

sealed class HomeState {
    object LOADING : HomeState()
    data class SUCCESS(
        val notes: List<NoteData>
    ): HomeState()
    object ERROR: HomeState()
}
