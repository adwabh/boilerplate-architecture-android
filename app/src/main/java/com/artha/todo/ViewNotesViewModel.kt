package com.artha.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artha.todo.data.NoteData
import com.artha.todo.data.NoteData.Companion.fromEntity
import com.artha.todo.data.NotesDao
import com.artha.todo.data.NotesEntity
import com.artha.todo.data.UserPreference
import com.artha.todo.network.NotesApiClient
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class ViewNotesViewModel @Inject constructor(private val repository: NoteDetailRepository, val gson: Gson) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNote(id: String): SharedFlow<ViewNotesState> = repository.getNoteData(id)
        .map { ViewNotesState.SUCCESS(it.fromEntity(gson)) }
        .shareIn(
            viewModelScope,
            SharingStarted.Eagerly
            )


}

interface NoteDetailRepository {
    fun getNoteData(id: String): Flow<NotesEntity>
}
class NoteDetailRepositoryImpl @Inject constructor(val apiClient: NotesApiClient, val notesDao: NotesDao, val userPreference: UserPreference) : NoteDetailRepository {
    override fun getNoteData(id: String): Flow<NotesEntity> {
        return notesDao.findNoteById(id, userPreference.getCurrentUser().id)
    }
}

sealed class ViewNotesState {
    data object LOADING : ViewNotesState()
    data class SUCCESS(
        val note: NoteData
    ): ViewNotesState()
    data object ERROR: ViewNotesState()
}
