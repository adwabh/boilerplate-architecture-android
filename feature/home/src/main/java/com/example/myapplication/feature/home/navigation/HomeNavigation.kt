package com.example.myapplication.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navArgument

const val homeScreenRoute: String = "notes_home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(homeScreenRoute, navOptions = navOptions)
