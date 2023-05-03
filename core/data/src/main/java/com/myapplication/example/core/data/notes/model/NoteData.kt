package com.myapplication.example.core.data.notes.model

data class NoteData(
    val id: String,
    val title: String = "",
    val body: String = "",
    val isChecklist: Boolean,
    val checkListItems: List<CheckListItem>
)