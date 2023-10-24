package com.artha.todo

import androidx.lifecycle.ViewModel
import com.artha.todo.network.NoteDetailResponse
import com.artha.todo.network.NotesApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.channelFlow
import retrofit2.awaitResponse
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class ViewNotesViewModel @Inject constructor(private val repository: NoteDetailRepository) : ViewModel() {
    fun getNote(id: String) = channelFlow<ViewNotesState> {
        repository.getNoteDetail(id)
            .map {
                ViewNotesState.SUCCESS(
                    it.toDetail()
                )
            }
    }
}

private fun NoteDetailResponse.toDetail(): NoteDetail = NoteDetail(
    id,
    createdDate,
    updatedDate,
    title,
    body
)

interface NoteDetailRepository {
    suspend fun getNoteDetail(id: String): Result<NoteDetailResponse>
}
class NoteDetailRepositoryImpl @Inject constructor(val apiClient: NotesApiClient) : NoteDetailRepository {
    override suspend fun getNoteDetail(id: String): Result<NoteDetailResponse> {
        val res = apiClient.getNoteDetail(id).awaitResponse()
        return if (res.isSuccessful) {
            res.body()?.let { Result.success(it) }?: Result.failure(Exception(""))
        } else {
            Result.failure(Exception("Error"))
        }
    }

}

sealed class ViewNotesState {
    data object LOADING : ViewNotesState()
    data class SUCCESS(
        val note: NoteDetail
    ): ViewNotesState()
    data object ERROR: ViewNotesState()
}

data class NoteDetail(
    val id: String,
    val createdDate: OffsetDateTime,
    val updatedDate: OffsetDateTime,
    val title: String,
    val body: String
)
