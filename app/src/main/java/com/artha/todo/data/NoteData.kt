package com.artha.todo.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class NoteData(
    val id: String,
    val title: String = "",
    val body: String = "",
    val type: String,
    val checkListItems: List<CheckListItem>,
    val date: OffsetDateTime,
    val userId: String
) {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun NotesEntity.fromEntity(gson: Gson): NoteData {
            val typeToken = object: TypeToken<List<CheckListItem>>() {}
            return NoteData(
                id = this.id,
                title = this.title,
                type = this.type,
                body = this.body,
                checkListItems = gson.fromJson(this.checkListItems, typeToken),
                date = OffsetDateTime.ofInstant(Instant.ofEpochMilli(this.date), ZoneOffset.UTC),
                userId = this.userId
            )
        }
    }
}