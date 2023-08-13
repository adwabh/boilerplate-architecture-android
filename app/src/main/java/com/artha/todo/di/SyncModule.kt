package com.artha.todo.di

import androidx.hilt.work.HiltWorkerFactory
import com.artha.todo.work.Sync
import com.artha.todo.work.SyncImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
abstract class SyncModule {
    @Binds
    abstract fun bindsSync(sync: SyncImpl): Sync
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltWorkerFactoryEntryPoint {
    fun hiltWorkerFactory(): HiltWorkerFactory
}
