package com.artha.todo

import android.util.Log
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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.shareIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    val state: SharedFlow<HomeState>
        get() {
            val state = repository.getNotes(userPreference.getCurrentUser(), 0, 0)
                .asResult()
                .map(::mapToState)
                .shareIn(
                    viewModelScope,
                    started = SharingStarted.Eagerly,
                )
            return state
        }
    private fun mapToState(notes: Result<List<NoteData>>): HomeState = when(notes) {
        is Result.Success -> {
            Log.d(TAG_STATE,"mapTOState success = ${notes.data}")
            HomeState.SUCCESS(notes.data)
        }
        is Result.Error -> {
            Log.d(TAG_STATE,"mapTOState Error ${notes.exception}")
            HomeState.ERROR
        }
        is Result.Loading -> {
            Log.d(TAG_STATE,"mapTOState Loading..")
            HomeState.LOADING
        }
    }
}

sealed class HomeState {
    data object LOADING : HomeState()
    data class SUCCESS(
        val notes: List<NoteData>
    ): HomeState()
    data object ERROR: HomeState()
}
