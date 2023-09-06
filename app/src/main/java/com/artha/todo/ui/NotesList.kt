package com.artha.todo.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artha.todo.data.NoteData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesList(
    notes: List<NoteData> = emptyList(),
    modifier: Modifier? = Modifier
        .fillMaxSize()
        .padding(8.dp),
    onNoteClick: (String) -> Unit
) {

    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)

    LazyVerticalStaggeredGrid(
        modifier = modifier!!,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        columns = cellConfiguration
    ) {
        items(
            key = { index -> notes[index].id },
            count = notes.size,
            itemContent = {
                NotesItem(data = notes[it], onItemClick = onNoteClick)
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun  NotesItemPreview() = NotesList(PreviewUtils.DUMMY_NOTES, onNoteClick = {})