package com.example.myapplication.feature.edit.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import java.util.Base64

const val editScreenRoute: String = "com.example.myapplication.notee/id = {id}"

//fun NavController.navigateToEditNote(navOptions: NavOptions? = null, noteId:String) = navigate(editScreenRoute, args = listOf(navArgument(Uri.encode(noteId))))