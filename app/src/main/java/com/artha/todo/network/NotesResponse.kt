package com.artha.todo.network

data class NotesResponse(
    val success : Boolean,
    val data: List<NotesDataResponse>?,
    val error: ErrorData
)

data class ErrorData(
    val code: String
)

data class NotesDataResponse(
    val id: String,
    val title: String = "",
    val body: String = "",
    val type: String,
    val checkListItems: List<CheckListItemResponse>,
    val date: String,
)

data class CheckListItemResponse(
    val title: String
)
