package com.example.myapplication.data.datasource.network

import com.example.myapplication.data.models.Movies
import retrofit2.Response

interface DataService {
    suspend fun getMovies() : Response<Movies>
}