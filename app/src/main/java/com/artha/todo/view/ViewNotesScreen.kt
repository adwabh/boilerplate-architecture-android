package com.artha.todo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artha.todo.ViewNotesViewModel
import com.artha.todo.ViewNotesViewModel.ViewNotesViewModel


@Composable
fun ViewNotesRoute(
    modifier: Modifier,
    viewModel: ViewNotesViewModel,
) {
    val state: viewModel.state.collectAsStateWithLifecycle(ViewNotesState.LOADING)
    ViewNotesScreen(
        modifier,
        state
    )
}
@Composable
fun ViewNotesScreen(modifier: Modifier, state: Any) {

}