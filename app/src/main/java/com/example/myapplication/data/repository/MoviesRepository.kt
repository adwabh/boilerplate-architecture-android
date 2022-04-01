package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.NetworkMovieDataSource
import com.example.myapplication.data.models.Either
import com.example.myapplication.data.models.Movies
import com.example.myapplication.data.models.Results
import javax.inject.Inject

interface MoviesRepository<T, E>  {
    suspend fun getMoviesData() : Either<T, E>
}

class MoviesRepositoryImpl @Inject constructor(private val dataSource : NetworkMovieDataSource) : MoviesRepository<List<Results>, Exception> {
    override suspend fun getMoviesData(): Either<List<Results>, Exception> = dataSource.getMoviesData()
}