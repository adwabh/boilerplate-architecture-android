package com.example.myapplication.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel : ViewModel() {

    val state: StateFlow<HomeState>
        get() {
            //TODO: implement this to transmit state
            return flowOf(HomeState.SUCCESS)
                .stateIn(viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = HomeState.LOADING,)
        }
}

sealed class HomeState {
    object LOADING : HomeState()
    object SUCCESS: HomeState()
    object ERROR: HomeState()
}
