package com.myapplication.example.core.data.notes

import com.example.myapplication.core.database.dao.NotesDao
import com.example.myapplication.core.database.entities.NotesEntity
import com.example.myapplication.core.network.NotesApiClient
import com.example.myapplication.core.network.responses.NotesDataResponse
import com.google.gson.Gson
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
    private val notesDao: NotesDao,
    private val notesApiClient: NotesApiClient,
    private val gson: Gson
): NotesRepository {
    override fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>> {
        return flow {
            notesDao.findNotes(user.id)
                .collect {
                    it.map {
                        entity -> entity.fromEntity(gson)
                    }
                }
        }
    }

    override suspend fun syncNotes(user: User): Boolean {
        val notesRes = notesApiClient
            .getNotes(user.id, 0, 0)
            .execute()
        val transform: (NotesDataResponse) -> NotesEntity = {
            it.toEntity(user, gson)
        }
        val isSuccess = notesRes.isSuccessful && notesRes.body()?.success?:false
        if(isSuccess) {
            val noteList = notesRes.body()!!.data
            val notes = noteList!!.map(transform).toTypedArray()
            notesDao.updateNotes(*notes)
        }
        return isSuccess
    }

}

private fun NotesDataResponse.toEntity(user: User, gson: Gson): NotesEntity = NotesEntity(
    id = this.id,
    title = this.title,
    body = this.body,
    type = this.type,
    checkListItems = gson.toJson(this.checkListItems),
    date = this.date.toLong(),
    userId = user.id
)

