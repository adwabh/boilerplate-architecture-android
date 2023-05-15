package com.example.myapplication.feature.home

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.core.ui.NotesList
import com.example.myapplication.core.ui.NotesTopBar


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val state = viewModel.state.collectAsState()
    HomeScreen(
        modifier,
        state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, state: State<HomeState>) {
    val snackbarHostState = remember { SnackbarHostState() }
    when(state.value) {
        HomeState.LOADING -> TODO()
        HomeState.SUCCESS -> {
            Scaffold(
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

            ) {
                paddingValues ->
                paddingValues
                NotesList(
                    notes
                )
            }
        }
        HomeState.ERROR -> {
            LaunchedEffect(snackbarHostState) {
                it
            }
        }
    }
}

