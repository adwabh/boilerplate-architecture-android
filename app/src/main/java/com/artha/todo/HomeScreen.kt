package com.artha.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artha.todo.data.NoteData
import com.artha.todo.ui.NotesList
import com.artha.todo.ui.NotesTopBar
import com.artha.todo.ui.PreviewUtils.DUMMY_NOTES
import java.time.OffsetDateTime


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        modifier,
        state
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier.fillMaxSize(), state: State<HomeState>) {
    val snackbarHostState = remember { SnackbarHostState() }
    when(val state = state.value) {
        HomeState.LOADING -> {
            Scaffold(
                modifier = modifier,
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

            ) {
                    paddingValues ->
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

@Preview
@Composable
fun HomeScreenPreview() = HomeScreen(modifier = Modifier.fillMaxSize(), state = object: State<HomeState> {
    override val value: HomeState
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return HomeState.SUCCESS(DUMMY_NOTES)
        }

})

