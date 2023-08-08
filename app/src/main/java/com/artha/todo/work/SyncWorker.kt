package com.artha.todo.work

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.artha.todo.data.UserPreference
import com.artha.todo.data.repo.NotesRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val SyncWorkName = "NOTES_SYNC_WORK"

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
object SyncImpl : Sync {

    override fun initialize(context: Context) {
        AppInitializer.getInstance(context)
            .initializeComponent(SyncInitializer::class.java)
    }

    private val SyncConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun sync(): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<SyncWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .build()
    }
}

object SyncInitializer : Initializer<SyncImpl> {

    override fun create(context: Context): SyncImpl {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncImpl.sync()
                )
        }
        return SyncImpl
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        WorkManagerInitializer::class.java)
}
interface Sync {
    abstract fun initialize(context: Context)
}
