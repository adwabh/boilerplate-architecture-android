package com.myapplication.example.core.data.notes

import com.example.myapplication.core.network.NotesApiClient
import com.myapplication.example.core.data.notes.model.NoteData
import com.myapplication.example.core.data.notes.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesRepository {
    fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>>
    suspend fun syncNotes(user: User): Boolean
}

class NotesRepositoryImpl @Inject constructor(

): NotesRepository {
    override fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>> {
        TODO("Not yet implemented")
    }

    override suspend fun syncNotes(user: User): Boolean {
        TODO("Not yet implemented")
    }

}
