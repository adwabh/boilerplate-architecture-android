package com.artha.todo

import android.app.Application
import com.artha.todo.work.SyncImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SyncImpl.initialize(this)
    }
}