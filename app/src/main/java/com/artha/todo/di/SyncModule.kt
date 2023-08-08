package com.artha.todo.di

import com.artha.todo.work.Sync
import com.artha.todo.work.SyncImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SyncModule {
    @Binds
    abstract fun bindsSync(sync: SyncImpl): Sync
}