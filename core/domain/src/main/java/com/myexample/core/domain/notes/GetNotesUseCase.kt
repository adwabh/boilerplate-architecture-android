package com.myexample.core.domain.notes

import com.myapplication.example.core.data.notes.NotesRepository
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime
import javax.inject.Inject

interface GetNotesUseCase {
    fun getNotes(user: User) : Flow<List<NotesData>>
}

class GetNotesUseCaseImpl @Inject constructor(
    val repository: NotesRepository
): GetNotesUseCase {
    override fun getNotes(user: User): Flow<List<NotesData>> {
        TODO("Not yet implemented")
    }

}

data class NotesData(
    val id: String,
    val title: String,
    val body: String,
    val type: String,
    val date: OffsetDateTime,
)

data class User(
    val id: String,
    val name: String,
    val email: String
)
