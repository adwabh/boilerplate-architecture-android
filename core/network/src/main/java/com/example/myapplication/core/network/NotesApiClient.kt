package com.example.myapplication.core.network

import com.example.myapplication.core.network.responses.NotesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NotesApiClient {

    @GET("/notes")
    fun getNotes(token:String, @Query("index") index: Int, @Query("offset") offset: Int): Call<NotesResponse>
}