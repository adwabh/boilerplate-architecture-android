package com.myexample.core.domain.notes

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNotesUseCase {
    fun getNotes(user: User) : Flow<List<NotesData>>
}


class GetNotesUseCaseImpl @Inject constructor(
    val repository: NotesRepository
): GetNotesUseCase {

}

class NotesData {

}

data class User(
    val id: String,
    val name: String,
    val email: String
)
