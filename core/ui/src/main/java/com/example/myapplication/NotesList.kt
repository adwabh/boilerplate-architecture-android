

package com.example.myapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NotesList(
   notes: List<NoteData> = emptyList()
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = StaggeredGridCells.Fixed(2)
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