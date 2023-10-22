package com.artha.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artha.todo.data.NoteData

@Preview
@Composable
fun NotesItem(
    onItemClick: (String) -> Unit = {},
    data: NoteData
//    isChecklist: Boolean,
//    checkListItems: List<CheckListItem>
) {
    val title = data.title
    val body = data.body
    if (title.isNotEmpty()) {
        NotesItemWithTitle(
            data,
            onItemClick
        )
    } else {
        NotesItemWithoutTitle(
            data,
            onItemClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesItemWithoutTitle(data: NoteData, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable {
                onItemClick(data.id)
            },
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { onItemClick.invoke(data.id) }
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = data.body
            )
        }
    }
}

@Preview
@Composable
fun NotesItemWithTitle(data: NoteData, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable {
                onItemClick(data.id)
            },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                fontSize = 20.sp,
                text = data.title
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )
            Text(
                text = data.body
            )
        }
    }
}
