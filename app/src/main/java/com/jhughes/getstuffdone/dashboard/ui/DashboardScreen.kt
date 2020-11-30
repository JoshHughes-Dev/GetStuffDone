package com.jhughes.getstuffdone.dashboard.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jhughes.getstuffdone.dashboard.DashboardViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {

    val groups = viewModel.getGroups().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GetStuffDone") },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.testCreateNewGroup()
            }) {
                Icon(asset = Icons.Outlined.Add)
            }
        },
    ) {
        ScrollableColumn {
            groups.value.forEach {
                Text(text = it.title)
                it.tasks.forEach { task ->
                    Text(text = task.toString())
                }
                Divider()
            }
        }
    }
}