package com.artha.todo.di

import com.artha.todo.network.NotesApiClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesRetrofit(gson: Gson): NotesApiClient = Retrofit.Builder()
        .baseUrl("https://notesapp.in")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(NotesApiClient::class.java)
}