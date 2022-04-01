package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Either
import com.example.myapplication.data.models.Movies
import com.example.myapplication.data.models.Results
import com.example.myapplication.data.repository.MoviesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepositoryImpl) :
    ViewModel() {

    fun fetchMovies() {
        viewModelScope.launch(viewModelScope.coroutineContext) {
            val either: Either<List<Results>, Exception> = repository.getMoviesData()
            if(either.isError()) {
                _error.postValue("Error Occurred, Try Again")
                return@launch
            }
            either.data?.let { _movies.postValue(it) }
        }
    }

    private var _movies : MutableLiveData<List<Results>> = MutableLiveData()
    val movies : LiveData<List<Results>> = _movies
    private var _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error
}