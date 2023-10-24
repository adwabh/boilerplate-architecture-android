package com.artha.todo.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.artha.todo.NoteDetail
import com.artha.todo.data.NoteData
import java.time.OffsetDateTime

object PreviewUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    val DUMMY_NOTES = listOf(
        NoteData(
            id = "NOTE001",
            title = "Somethings don't change",
            body = "In Life, there asre some things that change while others don't, and even with things that change it goes like the old saying that more things change more they stay the same.",
            type = "text",
            emptyList(),
            OffsetDateTime.now(),
            "G0010960"
        )
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val DUMMY_VIEW_DATA = NoteDetail(
        id = "NOTE001",
        title = "Somethings don't change",
        body = "In Life, there asre some things that change while others don't, and even with things that change it goes like the old saying that more things change more they stay the same.",
        createdDate = OffsetDateTime.now(),
        updatedDate = OffsetDateTime.now(),
    )
}