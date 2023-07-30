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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun NotesItem(
    title: String = "",
    body: String = "",
//    isChecklist: Boolean,
//    checkListItems: List<CheckListItem>
) {
    if(title.isNotEmpty()) {
        NotesItemWithTitle(
            title,
            body
        )
    } else {
        NotesItemWithoutTitle(
            body
        )
    }
}

@Composable
fun NotesItemWithoutTitle(body: String = "") {
    Card (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column (Modifier.padding(10.dp)){
            Text(
                text = body
            )
        }
    }
}

@Preview
@Composable
fun NotesItemWithTitle(title: String = "", body: String = "") {
    Card (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column (Modifier.padding(10.dp)){
            Text(
                fontSize = 20.sp,
                text = title
            )
            Spacer(modifier = Modifier
                .height(10.dp)
                .fillMaxWidth())
            Text(
                text = body
            )
        }
    }
}
