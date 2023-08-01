package com.artha.todo

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.artha.todo.ui.NotesList
import com.artha.todo.ui.NotesTopBar
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
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
    when(val state = state.value) {
        HomeState.LOADING -> {
            CircularProgressIndicator()
        }
        is HomeState.SUCCESS -> {
            Scaffold(
                modifier = modifier,
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

            ) {
                paddingValues ->
                paddingValues
                NotesList(state.notes)
            }
        }
        HomeState.ERROR -> {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    //FIXME: remove hardcoded message
                    message = "Something Went Wrong",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}

