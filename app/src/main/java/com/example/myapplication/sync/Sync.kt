package com.example.myapplication.sync

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer

private val SyncWorkName = "com.example.myapplication.sync.SyncWorker"

object Sync {
    fun initialize(context: Context) {
            AppInitializer.getInstance(context)
                .initializeComponent(NotesSync::class.java)
    }
}

class NotesSync : Initializer<Sync> {

    override fun create(context: Context): Sync {
        WorkManager.getInstance(context).apply {
            // Run sync on app startup and ensure only one sync worker runs at any time
            enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.,
            )
        }

        return Sync
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(WorkManagerInitializer::class.java)

}