package com.artha.todo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artha.todo.ui.theme.ComposeCodelabTheme
import com.artha.todo.view.ViewNotesRoute
import dagger.hilt.android.AndroidEntryPoint

const val homeRoute: String = "home"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesState = NotesState()

        setContent {
            ComposeCodelabTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    it
                    NotesHost(
                        state = notesState,
                        navController = rememberNavController(),

                    )
                }
            }
        }

    }
}

class NotesState {

}

@Composable
fun NotesHost(
    state: NotesState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        homeScreen(
            onNoteClick = {
                noteId -> navController.navigateToNote(noteId)
            }
        )
        viewNote()
    }

}

fun NavGraphBuilder.viewNote() {
    composable(
        route = "view_note/{noteId}",
        deepLinks = listOf(NavDeepLink("noteapp://view_note/{noteId}"))
    ) {
        val noteId = it.arguments?.getString("noteId")
        noteId?.let { id -> ViewNotesRoute(modifier = Modifier.fillMaxSize(), id = id) }
    }
}

private fun NavController.navigateToNote(noteId: String) {
    val encodedId = Uri.encode(noteId)
    navigate("view_note/$encodedId") {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.homeScreen(onNoteClick: (String)->Unit) {
    composable(
        route = homeRoute
    ) {
        HomeRoute(
            modifier = Modifier.fillMaxSize(),
            onNoteClick = onNoteClick
        )
    }
}


