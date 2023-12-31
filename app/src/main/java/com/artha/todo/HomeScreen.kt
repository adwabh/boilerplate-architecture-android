package com.artha.todo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artha.todo.ui.NotesList
import com.artha.todo.ui.NotesTopBar
import com.artha.todo.ui.PreviewUtils.DUMMY_NOTES

const val TAG_STATE = "HOME_STATE"


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNoteClick: (String) -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle(HomeState.LOADING)
    Log.d(TAG_STATE,"fetched state in home route ${state.value}")
    HomeScreen(
        modifier,
        state,
        onNoteClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    state: State<HomeState>,
    onNoteClick: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    when (val state = state.value) {
        is HomeState.LOADING -> {
            Scaffold(
                modifier = modifier,
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

            ) { paddingValues ->
                Box(modifier = modifier) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(paddingValues)
                    )
                }
            }
        }

        is HomeState.SUCCESS -> {
            Scaffold(
                modifier = modifier,
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                floatingActionButton = {
                    FloatingActionButton(onClick = ::onCreateNote) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Navigation icon")
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) { NotesList(state.notes, onNoteClick = onNoteClick) }
            }
        }

        is HomeState.ERROR -> {
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

fun onCreateNote() {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun HomeScreenPreview() {
    val state = object : State<HomeState> {
        override val value: HomeState
            @RequiresApi(Build.VERSION_CODES.O)
            get() {
                return HomeState.SUCCESS(DUMMY_NOTES)
            }

    }
    HomeScreen(modifier = Modifier.fillMaxSize(), state = state, onNoteClick = {})
}

