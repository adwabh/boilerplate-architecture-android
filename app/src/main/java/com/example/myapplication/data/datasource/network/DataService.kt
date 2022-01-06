package com.example.myapplication.data.datasource.network

import com.example.myapplication.data.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface DataService {
    @GET("3/movie/popular")
    suspend fun getMovies(@HeaderMap headers: Map<String, String?>): Response<Movies>
}