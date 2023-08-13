package com.artha.todo.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.artha.todo.data.UserPreference
import com.artha.todo.data.repo.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workParams: WorkerParameters,
    private val repository: NotesRepository,
    private val userPreference: UserPreference
) : CoroutineWorker(context, workParams){

    override suspend fun doWork(): Result {
//        return Result.success()
        val result = repository.syncNotes(userPreference.getCurrentUser())

        return if(result) Result.success() else Result.retry()
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

@Module
@InstallIn(SingletonComponent::class)
object SyncInitializer : Initializer<WorkManager> {

    @Provides
    override fun create(context: Context): WorkManager {
        val instance = WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                SyncWorker::class.java.canonicalName,
                ExistingWorkPolicy.KEEP,
                SyncImpl.sync()
                )
        }
        return instance
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
interface Sync {
    abstract fun initialize(context: Context)
}
