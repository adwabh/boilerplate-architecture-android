package com.example.myapplication.core.ui

import androidx.compose.runtime.Composable
import java.time.OffsetDateTime

@Composable
fun noteItem(
    title: String,
    body: String,
    createdDate: OffsetDateTime,
    updatedDate: OffsetDateTime,
    isChecklist: Boolean,
    checkListItems: List<CheckListItem>
) {

}

data class CheckListItem(
    val title: String,
    val isChecked: Boolean
)