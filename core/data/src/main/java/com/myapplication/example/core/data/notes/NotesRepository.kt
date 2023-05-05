package com.myapplication.example.core.data.notes

import com.example.myapplication.core.database.dao.NotesDao
import com.myapplication.example.core.data.notes.model.NoteData
import com.myapplication.example.core.data.notes.model.NoteData.Companion.fromEntity
import com.myapplication.example.core.data.notes.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NotesRepository {
    fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>>
    suspend fun syncNotes(user: User): Boolean
}

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>> {
        return flow {
            notesDao.findNotes(user.id)
                .collect {
                    it.map {
                        entity -> entity.fromEntity()
                    }
                }
        }
    }

    override suspend fun syncNotes(user: User): Boolean {
        TODO("Not yet implemented")
    }

}
