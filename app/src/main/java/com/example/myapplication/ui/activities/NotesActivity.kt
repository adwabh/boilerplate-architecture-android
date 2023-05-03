package com.example.myapplication.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.core.ui.NoteData
import com.example.myapplication.core.ui.NotesList
import com.example.myapplication.core.ui.NotesTopBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NotesActivity : ComponentActivity() {

    @Inject
    lateinit var getNotesUseCase: GetNotesUseCase

    private lateinit var notes: MutableList<NoteData>

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notes = mutableListOf<NoteData>().apply {
            add(NoteData("G0010960", "Noteworthy", "Lorem ipsum dolor sit amet", false, emptyList()))
            add(NoteData("G0010961", "Nautilus", "Lorem ipsum dolor sit amet", false, emptyList()))
            add(NoteData("G0010962", "Shopping", "Lorem ipsum dolor sit amet consehsjh jadkashladlsdald", false, emptyList()))
            add(NoteData("G0010963", "Something", "Lorem ipsum dolor sit ametoiisnsjksbak asjjbkabckja", false, emptyList()))
            add(NoteData("G0010964", "Noteworthy", "Lorem ipsum dolor sit amet ajbskjas ajksbbkjafbkabfk f aksbksajfbkjabfakjbbakfbkabkf akjbfkjabbaskbfkb", false, emptyList()))
            add(NoteData("G0010965", "Nautilus", "Lorem ipsum dolor sit amet sabjkafbjbfkafbkjf", false, emptyList()))
            add(NoteData("G0010966", "Shopping", "Lorem ipsum dolor sit amet consehsjh jadkashladlsdald jksddhlsa;sfhkmfhlk", false, emptyList()))
            add(NoteData("G0010967", "Something", "Lorem ipsum dolor sit ametoiisnsjksbak asjjbkabckja vajsbjsaflalfkhaflskafhlfh ushhavjcvackcaskjc  ascbkjscakjc skbckabkcbacnaklcnaskc akscbkjcbakcsbcakjbskjcbjabckkc msc asckjsbckjabc", false, emptyList()))
        }
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                topBar = { NotesTopBar() },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

            ) {
                if (isError) {
                    LaunchedEffect(snackbarHostState) {
                        it
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}