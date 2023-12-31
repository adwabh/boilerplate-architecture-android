package com.artha.todo.data.repo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.artha.todo.TAG_STATE
import com.artha.todo.data.NoteData
import com.artha.todo.data.NoteData.Companion.fromEntity
import com.artha.todo.data.NotesDao
import com.artha.todo.data.NotesEntity
import com.artha.todo.data.User
import com.artha.todo.network.NotesApiClient
import com.artha.todo.network.NotesDataResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface NotesRepository {
    fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>>
    suspend fun syncNotes(user: User): Boolean
}

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val notesApiClient: NotesApiClient,
    private val gson: Gson
) : NotesRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    val mapper: suspend (value: List<NotesEntity>) ->List<NoteData> = {
        Log.d(TAG_STATE,"fetched db entity list = $it")
        it.map { entity ->
            entity.fromEntity(gson)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNotes(user: User, index: Int, offset: Int): Flow<List<NoteData>> {
        Log.d(TAG_STATE,"calling notesDao.findNotes for ${user.id}")
        return notesDao.findNotes(user.id)
            .map(mapper)
    }

    override suspend fun syncNotes(user: User): Boolean {
        val notesRes = notesApiClient
            .getNotes(user.id, 0, 0)
            .execute()
        val transform: (NotesDataResponse) -> NotesEntity = {
            it.toEntity(user, gson)
        }
        val isSuccess = notesRes.isSuccessful && notesRes.body()?.success ?: false && notesRes.body()!= null

        if (isSuccess) {
            val noteList = notesRes.body()?.data?: emptyList()
            Log.d(TAG_STATE,"API Notes success = $noteList")
            val notes = noteList.map(transform)
            Log.d(TAG_STATE,"sync updating db entity = $notes")
            val notes1 = notes.toTypedArray()
            notesDao.updateNotes(*notes1)
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

