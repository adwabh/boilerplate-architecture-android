package com.example.myapplication.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.network.result.Result
import com.example.myapplication.core.network.result.asResult
import com.myapplication.example.core.data.notes.NotesRepository
import com.myapplication.example.core.data.notes.UserPreference
import com.myapplication.example.core.data.notes.model.NoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: NotesRepository,
    val userPreference: UserPreference
) : ViewModel() {

    val state: StateFlow<HomeState>
        get() {
            return repository.getNotes(userPreference.getCurrentUser(), 0,0)
                .asResult()
                .map(::mapToState)
                .stateIn(viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = HomeState.LOADING)
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
