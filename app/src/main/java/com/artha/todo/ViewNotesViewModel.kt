package com.artha.todo

import androidx.lifecycle.ViewModel
import com.artha.todo.network.NotesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class ViewNotesViewModel @Inject constructor(repository: NoteDetailRepository) : ViewModel() {

}

interface NoteDetailRepository {
    suspend fun getNoteDetail(id: String): Flow<NoteDetail>
}
class NoteDetailRepositoryImpl @Inject constructor(val apiClient: NotesApiClient) : NoteDetailRepository {
    override suspend fun getNoteDetail(id: String): Flow<NoteDetail> {
        apiClient.getNoteDetail(id)
    }

}

sealed class ViewNotesState {
    data object LOADING : HomeState()
    data class SUCCESS(
        val note: NoteDetail
    ): HomeState()
    data object ERROR: HomeState()
}

data class NoteDetail(
    val id: String,
    val createdDate: OffsetDateTime,
    val updatedDate: OffsetDateTime,
    val title: String,
    val body: String
)
