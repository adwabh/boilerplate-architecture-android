package com.example.myapplication.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.NoteData
import com.example.myapplication.NotesList
import com.example.myapplication.ui.theme.MyApplicationTheme

class NotesActivity : ComponentActivity() {
    private lateinit var notes: MutableList<NoteData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notes = mutableListOf<NoteData>().apply {
            add(NoteData("G0010960", "Noteworthy", "Lorem ipsum dolor sit amet", false, emptyList()))
            add(NoteData("G0010961", "Nautilus", "Lorem ipsum dolor sit amet", false, emptyList()))
        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesList(
                        notes
                    )
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