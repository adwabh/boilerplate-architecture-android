package com.artha.todo.data.repo

import android.os.Build
import androidx.annotation.RequiresApi
import com.artha.todo.data.NoteData
import com.artha.todo.data.NoteData.Companion.fromEntity
import com.artha.todo.data.NotesDao
import com.artha.todo.data.NotesEntity
import com.artha.todo.data.User
import com.artha.todo.network.NotesApiClient
import com.artha.todo.network.NotesDataResponse
import com.google.gson.Gson
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
    @RequiresApi(Build.VERSION_CODES.O)
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

