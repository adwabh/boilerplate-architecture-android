package com.example.myapplication.ui.navigation

import android.graphics.drawable.Icon
import com.example.myapplication.R

enum class TopLevelDestination(
    val titleTextId: Int,
) {
    HOME(
        titleTextId = R.string.home
    ),

    VIEW_NOTE(
        titleTextId = R.string.view_note
    ),

    EDIT_NOTE(
        titleTextId = R.string.edit_note
    )
}