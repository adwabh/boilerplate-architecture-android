package com.myapplication.example.core.data.notes.model

import com.example.myapplication.core.database.entities.NotesEntity
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class NoteData(
    val id: String,
    val title: String = "",
    val body: String = "",
    val isChecklist: Boolean,
    val checkListItems: List<CheckListItem>,
    val date: OffsetDateTime,
    val userId: String
) {

    companion object {
        fun NotesEntity.fromEntity(): NoteData = NoteData(
            id = this.id,
            title = this.title,
            body = this.body,
            isChecklist = false,
            checkListItems = emptyList(),
            date = OffsetDateTime.ofInstant(Instant.ofEpochMilli(this.date), ZoneOffset.UTC),
            userId = this.userId
        )
    }
}