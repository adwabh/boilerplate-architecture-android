package com.artha.todo.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artha.todo.HomeState
import com.artha.todo.NoteDetail
import com.artha.todo.ViewNotesState
import com.artha.todo.ViewNotesViewModel
import com.artha.todo.ui.NotesTopBar
import com.artha.todo.ui.PreviewUtils.DUMMY_VIEW_DATA

@Composable
fun ViewNotesRoute(
    modifier: Modifier,
    viewModel: ViewNotesViewModel = hiltViewModel(),
    id: String
) {
    val state = viewModel.getNote(id).collectAsStateWithLifecycle(ViewNotesState.LOADING)
    ViewNotesScreen(
        modifier,
        state
    )
}

@Composable
fun ViewNotesScreen(modifier: Modifier, state: State<ViewNotesState>) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(modifier = modifier,
        topBar = { NotesTopBar() },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            when (val state = state.value) {
                is ViewNotesState.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                is ViewNotesState.SUCCESS -> {
                    Column {
                        with(state.note) {
                            Text(text = title)
                            Text(text = body)
                        }
                    }
                }

                is ViewNotesState.ERROR -> {
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
    }
}

@Preview
@Composable
fun ViewNoteScreenPreview() {
    val sampleState = object: State<ViewNotesState> {
        override val value: ViewNotesState
            @RequiresApi(Build.VERSION_CODES.O)
            get() = ViewNotesState.SUCCESS(DUMMY_VIEW_DATA)
    }
    ViewNotesScreen(modifier = Modifier.fillMaxSize(), state = sampleState)
}