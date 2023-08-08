package com.artha.todo.work

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.artha.todo.data.UserPreference
import com.artha.todo.data.repo.NotesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SyncWorker @Inject constructor(
    context: Context,
    workParams: WorkerParameters,
    private val repository: NotesRepository,
    private val userPreference: UserPreference
) : Worker(context, workParams){
    override fun doWork(): Result {
        val result = runBlocking {
            repository.syncNotes(userPreference.getCurrentUser())
        }
        return if(result) Result.success() else Result.failure()
    }
}
class SyncImpl @Inject constructor(@ApplicationContext val context: Context) : Sync {
    override fun sync() {
        val request = OneTimeWorkRequestBuilder<SyncWorker>()
            .build()
        WorkManager.getInstance(context)
            .enqueue(request)
    }

}
interface Sync {
    abstract fun sync()
}
