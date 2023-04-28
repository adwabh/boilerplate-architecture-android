package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun NotesList(
   notes: List<NoteData> = emptyList()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            key = { index -> notes[index].id },
            count = notes.size,
            itemContent = {
                NotesItem(
                    notes[it].title,
                    notes[it].body
                )
            }
        )
    }
}

data class NoteData(
    val id: String,
    val title: String = "",
    val body: String = "",
    val isChecklist: Boolean,
    val checkListItems: List<CheckListItem>
)