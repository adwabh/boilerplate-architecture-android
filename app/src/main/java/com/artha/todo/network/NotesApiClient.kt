package com.artha.todo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.OffsetDateTime

interface NotesApiClient {
    @GET("api/v1/notes")
    fun getNotes(@Header("token") token:String, @Query("index") index: Int, @Query("offset") offset: Int): Call<NotesResponse>
    @GET("api/v1/note/{id}")
    fun getNoteDetail(@Path("id") id: String): Call<NoteDetailResponse>
}

data class NoteDetailResponse (
    val id: String,
    val createdDate: OffsetDateTime,
    val updatedDate: OffsetDateTime,
    val title: String,
    val body: String
)
