package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.datasource.NetworkMovieDataSource
import com.example.myapplication.data.datasource.network.AuthServiceImpl
import com.example.myapplication.data.datasource.network.DataService
import com.example.myapplication.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {

    @ViewModelScoped
    @Provides
    fun providesAuthService(@ApplicationContext appContext: Context) : AuthServiceImpl = AuthServiceImpl(appContext)

    @ViewModelScoped
    @Provides
    fun providesDataSource(dataService: DataService, authService: AuthServiceImpl) : NetworkMovieDataSource = NetworkMovieDataSource(service = dataService, authService = authService)

    @ViewModelScoped
    @Provides
    fun providesRepository(dataSource: NetworkMovieDataSource) : MoviesRepositoryImpl = MoviesRepositoryImpl(dataSource = dataSource)
}