package com.myexample.core.domain.notes

import com.myapplication.example.core.data.notes.NotesRepository
import com.myapplication.example.core.data.notes.UserPreference
import com.myapplication.example.core.data.notes.model.NoteData
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime
import java.util.prefs.AbstractPreferences
import javax.inject.Inject

interface GetNotesUseCase {
    fun getNotes(user: User) : Flow<List<NoteData>>
}

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository,
    private val userPreference: UserPreference
): GetNotesUseCase {
    override fun getNotes(user: User): Flow<List<NoteData>> {
        return repository.getNotes(userPreference.getCurrentUser(),
            0,
            0)
    }

}

data class User(
    val id: String,
    val name: String,
    val email: String
)
