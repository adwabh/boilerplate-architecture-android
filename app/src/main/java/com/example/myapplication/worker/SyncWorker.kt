package com.example.myapplication.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.myapplication.example.core.data.notes.NotesRepository
import com.myapplication.example.core.data.notes.model.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject


@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: NotesRepository,
    val userPref: UserPreferences
) : CoroutineWorker(appContext,
    params) {
    override suspend fun doWork(): Result {
        return if(repository.syncNotes(userPref.getUser())) Result.success() else Result.failure()
    }

}

class UserPreferences {
    fun getUser(): User {
        TODO("Not yet implemented")
    }

}
