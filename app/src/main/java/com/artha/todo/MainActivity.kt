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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artha.todo.ui.theme.ComposeCodelabTheme

const val homeRoute: String = "home"

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
    }

}

private fun NavController.navigateToNote(noteId: String) {
    val encodedId = Uri.encode(noteId)
    this.navigate("note_view_route/$encodedId") {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.homeScreen(onNoteClick: (String)->Unit) {
    composable(
        route = homeRoute
    ) {
        HomeRoute()
    }
}


