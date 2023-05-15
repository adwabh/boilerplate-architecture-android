package com.example.myapplication

import android.app.Application
import com.example.myapplication.sync.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //initialize sync
        Sync.initialize(this)
    }
}