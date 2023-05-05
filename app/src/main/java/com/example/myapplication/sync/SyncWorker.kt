package com.example.myapplication.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.myapplication.example.core.data.notes.NotesRepository
import com.myapplication.example.core.data.notes.model.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


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

    companion object {
        /**
         * Expedited one time work to sync data on app startup
         */
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }

}

class UserPreferences {
    fun getUser(): User {
        TODO("Not yet implemented")
    }

}
