package com.artha.todo.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artha.todo.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NotesTopBar() {
    return TopAppBar(
        title = { Text(text = "Notes") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        navigationIcon = {
//          IconToggleButton(
//              checked = false,
//              onCheckedChange = ::onMenuClick) {
//              painterResource(
//                  id = R.drawable.ic_nav_bar)
//          }
             IconButton(onClick = { /*TODO*/ },
             ) {
                 painterResource(id = R.drawable.ic_nav_bar)
             }
        }

        /*actions = {
            NavigationBarItem(selected = false, onClick = {

            }) {

            }
        }*/
//        actionData = listOf(MenuAction.Save),
        /*action = { menuAction ->
            AppBarIcon(icon = imageResource(
                id = menuAction.icon)) {
                // Handle action click
            }
        }*/
    )
}

fun onMenuClick(checked: Boolean) {
    TODO("Not yet implemented")
}
