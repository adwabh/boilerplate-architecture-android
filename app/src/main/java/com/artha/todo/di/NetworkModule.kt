package com.artha.todo.di

import com.artha.todo.network.NotesApiClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(
                    HttpLoggingInterceptor
                        .Level
                        .BODY
                )
        ).build()

    @Provides
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): NotesApiClient =
        Retrofit.Builder()
            .baseUrl("https://demo3577351.mockable.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(NotesApiClient::class.java)
}