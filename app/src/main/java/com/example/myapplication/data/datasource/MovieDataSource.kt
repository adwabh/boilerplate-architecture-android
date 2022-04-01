package com.example.myapplication.data.datasource

import com.example.myapplication.data.datasource.network.AuthService
import com.example.myapplication.data.datasource.network.AuthServiceImpl
import com.example.myapplication.data.datasource.network.DataService
import com.example.myapplication.data.models.Either
import com.example.myapplication.data.models.Movies
import com.example.myapplication.data.models.Results
import java.io.IOException
import javax.inject.Inject

interface MovieDataSource<T, E> {
    suspend fun getMoviesData() : Either<T, E>
}

class NetworkMovieDataSource @Inject constructor(val service: DataService, val authService: AuthServiceImpl) : MovieDataSource<List<Results>, Exception>{
    override suspend fun getMoviesData(): Either<List<Results>, Exception> {
        var data: List<Results>? = null
        var error: Exception? = null
        try {
            val res = service.getMovies(authService.getHeaders())
            data = res.body()?.results
        } catch (e:IOException) {
            error = e
        }
        return Either(data, error)
    }

}