package com.artha.todo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NotesApiClient {

    @GET("api/v1/notes")
    fun getNotes(@Header("token") token:String, @Query("index") index: Int, @Query("offset") offset: Int): Call<NotesResponse>
}